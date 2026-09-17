package com.example.data.model

data class Chapter(
    val title: String,
    val pageNumber: Int
)

data class Book(
    val id: String,
    val title: String,
    val subjectId: String,
    val grade: Grade,
    val sizeMb: Double,
    val totalPages: Int,
    val editionYear: String,
    val publisher: String,
    val description: String,
    val remotePdfUrl: String,
    val chapters: List<Chapter>
)

sealed class BookDownloadStatus {
    object NotDownloaded : BookDownloadStatus()
    data class Downloading(val progressPercent: Int, val bytesDownloaded: Long, val totalBytes: Long) : BookDownloadStatus()
    data class Downloaded(val localFilePath: String, val downloadedTimestamp: Long) : BookDownloadStatus()
    data class Error(val errorMessage: String) : BookDownloadStatus()
}

data class BookWithStatus(
    val book: Book,
    val status: BookDownloadStatus,
    val lastReadPage: Int? = null,
    val totalProgressPercent: Int = 0
)
