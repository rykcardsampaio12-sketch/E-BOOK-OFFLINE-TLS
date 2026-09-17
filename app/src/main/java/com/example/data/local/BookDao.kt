package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM downloaded_books ORDER BY downloadedAt DESC")
    fun getAllDownloadedBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM downloaded_books WHERE bookId = :bookId LIMIT 1")
    fun getDownloadedBook(bookId: String): Flow<BookEntity?>

    @Query("SELECT * FROM downloaded_books WHERE bookId = :bookId LIMIT 1")
    suspend fun getDownloadedBookSync(bookId: String): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedBook(book: BookEntity)

    @Query("DELETE FROM downloaded_books WHERE bookId = :bookId")
    suspend fun deleteDownloadedBook(bookId: String)

    // Bookmarks
    @Query("SELECT * FROM bookmarks WHERE bookId = :bookId ORDER BY pageNumber ASC")
    fun getBookmarksForBook(bookId: String): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :bookmarkId")
    suspend fun deleteBookmark(bookmarkId: Long)

    @Query("DELETE FROM bookmarks WHERE bookId = :bookId AND pageNumber = :pageNumber")
    suspend fun deleteBookmarkByPage(bookId: String, pageNumber: Int)

    // Reading progress
    @Query("SELECT * FROM reading_progress")
    fun getAllReadingProgress(): Flow<List<ReadingProgressEntity>>

    @Query("SELECT * FROM reading_progress WHERE bookId = :bookId LIMIT 1")
    fun getReadingProgress(bookId: String): Flow<ReadingProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReadingProgress(progress: ReadingProgressEntity)
}
