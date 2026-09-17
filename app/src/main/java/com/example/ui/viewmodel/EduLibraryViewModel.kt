package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.BookmarkEntity
import com.example.data.model.Book
import com.example.data.model.BookWithStatus
import com.example.data.model.CurriculumSubjects
import com.example.data.model.Grade
import com.example.data.model.Subject
import com.example.data.repository.BookRepository
import com.example.data.repository.NearbyPeer
import com.example.data.repository.P2PManager
import com.example.data.repository.P2PTransferStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import java.util.Locale

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    data class NavigateToReader(val book: Book, val file: File) : UiEvent()
}

class EduLibraryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BookRepository(application)
    val p2pManager = P2PManager()

    // Navigation and filters
    private val _selectedGrade = MutableStateFlow(Grade.GRADE_7)
    val selectedGrade: StateFlow<Grade> = _selectedGrade.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedSubject = MutableStateFlow<Subject?>(null)
    val selectedSubject: StateFlow<Subject?> = _selectedSubject.asStateFlow()

    // Active reading book
    private val _activeReadingBook = MutableStateFlow<Book?>(null)
    val activeReadingBook: StateFlow<Book?> = _activeReadingBook.asStateFlow()

    private val _activeReadingFile = MutableStateFlow<File?>(null)
    val activeReadingFile: StateFlow<File?> = _activeReadingFile.asStateFlow()

    // P2P Sharing Modal state
    private val _p2pSharingBook = MutableStateFlow<Book?>(null)
    val p2pSharingBook: StateFlow<Book?> = _p2pSharingBook.asStateFlow()

    // Storage info
    private val _storageInfo = MutableStateFlow("0.0 MB")
    val storageInfo: StateFlow<String> = _storageInfo.asStateFlow()

    // One-time UI events
    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    // Downloaded books (for offline shelf on home screen)
    val downloadedBooks: StateFlow<List<BookWithStatus>> = repository.getAllDownloadedBooksWithStatus()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Filtered books for current grade and search
    @OptIn(ExperimentalCoroutinesApi::class)
    val booksForGrade: StateFlow<List<BookWithStatus>> = combine(
        _selectedGrade,
        _searchQuery
    ) { grade, query ->
        grade to query
    }.flatMapLatest { (grade, query) ->
        repository.getBooksWithStatus(grade, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Bookmarks for the active book
    @OptIn(ExperimentalCoroutinesApi::class)
    val activeBookmarks: StateFlow<List<BookmarkEntity>> = _activeReadingBook
        .flatMapLatest { book ->
            if (book != null) repository.getBookmarks(book.id) else flowOf(emptyList())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        updateStorageInfo()
    }

    fun selectGrade(grade: Grade) {
        _selectedGrade.value = grade
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun selectSubject(subject: Subject?) {
        _selectedSubject.value = subject
    }

    fun onBookTapped(book: Book) {
        viewModelScope.launch {
            val isDownloaded = repository.isBookDownloaded(book.id)
            if (isDownloaded) {
                // Open immediately offline!
                val file = repository.getLocalBookFile(book.id)
                if (file != null) {
                    _activeReadingBook.value = book
                    _activeReadingFile.value = file
                    _uiEvents.emit(UiEvent.NavigateToReader(book, file))
                }
            } else {
                // Download from cloud once, then open
                _uiEvents.emit(UiEvent.ShowToast("Haú husik download livru '${book.title}'..."))
                val result = repository.downloadBook(book)
                result.onSuccess { file ->
                    updateStorageInfo()
                    _activeReadingBook.value = book
                    _activeReadingFile.value = file
                    _uiEvents.emit(UiEvent.ShowToast("Livru rai ona iha memória lokál! Lee offline livre."))
                    _uiEvents.emit(UiEvent.NavigateToReader(book, file))
                }.onFailure { error ->
                    _uiEvents.emit(UiEvent.ShowToast("Download la susesu: ${error.localizedMessage}"))
                }
            }
        }
    }

    fun downloadBookOnly(book: Book) {
        viewModelScope.launch {
            val result = repository.downloadBook(book)
            result.onSuccess {
                updateStorageInfo()
                _uiEvents.emit(UiEvent.ShowToast("Livru '${book.title}' salva ona ba memória offline!"))
            }.onFailure { error ->
                _uiEvents.emit(UiEvent.ShowToast("Download falla: ${error.localizedMessage}"))
            }
        }
    }

    fun deleteDownloadedBook(book: Book) {
        viewModelScope.launch {
            repository.deleteDownloadedBook(book.id)
            updateStorageInfo()
            _uiEvents.emit(UiEvent.ShowToast("Livru '${book.title}' hamoos ona husi memória."))
        }
    }

    fun openP2PShare(book: Book) {
        _p2pSharingBook.value = book
        p2pManager.reset()
        viewModelScope.launch {
            p2pManager.startDiscovery()
        }
    }

    fun closeP2PShare() {
        _p2pSharingBook.value = null
        p2pManager.reset()
    }

    fun sendBookToPeer(peer: NearbyPeer) {
        val book = _p2pSharingBook.value ?: return
        viewModelScope.launch {
            p2pManager.sendBookToPeer(book, peer)
        }
    }

    fun receiveBookSimulated(peer: NearbyPeer, bookToReceive: Book) {
        viewModelScope.launch {
            p2pManager.receiveBookFromPeer(peer, bookToReceive.title) {
                // Persist the received book locally
                repository.downloadBook(bookToReceive)
                updateStorageInfo()
            }
        }
    }

    fun toggleBookmark(bookId: String, pageNumber: Int) {
        viewModelScope.launch {
            repository.toggleBookmark(bookId, pageNumber)
        }
    }

    fun removeBookmark(bookmarkId: Long) {
        viewModelScope.launch {
            repository.removeBookmark(bookmarkId)
        }
    }

    fun saveProgress(bookId: String, page: Int, totalPages: Int) {
        viewModelScope.launch {
            repository.saveReadingProgress(bookId, page, totalPages)
        }
    }

    fun closeReader() {
        _activeReadingBook.value = null
        _activeReadingFile.value = null
    }

    private fun updateStorageInfo() {
        val bytes = repository.getTotalStorageUsedBytes()
        val mb = bytes.toDouble() / (1024 * 1024)
        _storageInfo.value = String.format(Locale.US, "%.1f MB", mb)
    }
}
