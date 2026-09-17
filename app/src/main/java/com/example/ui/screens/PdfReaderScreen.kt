package com.example.ui.screens

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.BookmarkEntity
import com.example.data.model.Book
import com.example.ui.theme.ReaderSepiaBg
import com.example.ui.theme.ReaderSepiaText
import com.example.ui.theme.TimorGold
import com.example.ui.theme.TimorNavy
import com.example.ui.theme.TimorRed
import com.example.ui.viewmodel.EduLibraryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

enum class ReadingMode {
    LIGHT,
    DARK,
    SEPIA
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfReaderScreen(
    book: Book,
    file: File,
    viewModel: EduLibraryViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bookmarks by viewModel.activeBookmarks.collectAsState()
    val scope = rememberCoroutineScope()

    var currentPage by remember { mutableIntStateOf(0) }
    var totalPages by remember { mutableIntStateOf(1) }
    var currentBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoadingPage by remember { mutableStateOf(true) }

    // Reading Display Modes
    var readingMode by remember { mutableStateOf(ReadingMode.LIGHT) }

    // Zoom & Pan state
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    // Sheet visibility
    var showBookmarksSheet by remember { mutableStateOf(false) }
    var showChaptersSheet by remember { mutableStateOf(false) }

    // Setup PdfRenderer
    var pdfRenderer by remember { mutableStateOf<PdfRenderer?>(null) }
    var fileDescriptor by remember { mutableStateOf<ParcelFileDescriptor?>(null) }

    // Initialize PdfRenderer on IO
    DisposableEffect(file) {
        var pfd: ParcelFileDescriptor? = null
        var renderer: PdfRenderer? = null
        try {
            pfd = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            renderer = PdfRenderer(pfd)
            fileDescriptor = pfd
            pdfRenderer = renderer
            totalPages = renderer.pageCount
        } catch (e: Exception) {
            e.printStackTrace()
        }

        onDispose {
            try {
                renderer?.close()
                pfd?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Render current page when currentPage or pdfRenderer changes
    LaunchedEffect(currentPage, pdfRenderer) {
        val renderer = pdfRenderer ?: return@LaunchedEffect
        if (currentPage < 0 || currentPage >= renderer.pageCount) return@LaunchedEffect

        isLoadingPage = true
        withContext(Dispatchers.IO) {
            try {
                val page = renderer.openPage(currentPage)
                // Render at high density for sharp text
                val densityFactor = 2
                val width = page.width * densityFactor
                val height = page.height * densityFactor
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                page.close()

                withContext(Dispatchers.Main) {
                    currentBitmap = bitmap
                    isLoadingPage = false
                    // Persist reading progress
                    viewModel.saveProgress(book.id, currentPage + 1, totalPages)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    isLoadingPage = false
                }
            }
        }
    }

    val isCurrentPageBookmarked = bookmarks.any { it.pageNumber == currentPage + 1 }

    // Color theme configuration
    val backgroundColor = when (readingMode) {
        ReadingMode.LIGHT -> Color(0xFFF1F5F9)
        ReadingMode.DARK -> Color(0xFF0F172A)
        ReadingMode.SEPIA -> ReaderSepiaBg
    }

    val topBarColor = when (readingMode) {
        ReadingMode.LIGHT -> MaterialTheme.colorScheme.surface
        ReadingMode.DARK -> Color(0xFF1E293B)
        ReadingMode.SEPIA -> Color(0xFFF4E4C1)
    }

    val contentColor = when (readingMode) {
        ReadingMode.LIGHT -> MaterialTheme.colorScheme.onSurface
        ReadingMode.DARK -> Color.White
        ReadingMode.SEPIA -> ReaderSepiaText
    }

    // Color filter for night mode / sepia inversion
    val imageColorFilter: ColorFilter? = when (readingMode) {
        ReadingMode.DARK -> {
            val invertMatrix = ColorMatrix(
                floatArrayOf(
                    -1f, 0f, 0f, 0f, 255f,
                    0f, -1f, 0f, 0f, 255f,
                    0f, 0f, -1f, 0f, 255f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
            ColorFilter.colorMatrix(invertMatrix)
        }
        ReadingMode.SEPIA -> null
        ReadingMode.LIGHT -> null
    }

    val transformableState = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale = (scale * zoomChange).coerceIn(1f, 3.5f)
        if (scale == 1f) {
            offset = Offset.Zero
        } else {
            offset += offsetChange
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding()
            .testTag("pdf_reader_screen")
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Controls Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = topBarColor,
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("reader_back_btn")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Fila",
                            tint = contentColor
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = contentColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Pájina ${currentPage + 1} de $totalPages • ${book.grade.displayName}",
                            style = MaterialTheme.typography.labelSmall,
                            color = contentColor.copy(alpha = 0.7f)
                        )
                    }

                    // Reading Mode toggle (Light / Sepia / Dark)
                    IconButton(
                        onClick = {
                            readingMode = when (readingMode) {
                                ReadingMode.LIGHT -> ReadingMode.SEPIA
                                ReadingMode.SEPIA -> ReadingMode.DARK
                                ReadingMode.DARK -> ReadingMode.LIGHT
                            }
                        },
                        modifier = Modifier.testTag("reader_mode_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Brightness4,
                            contentDescription = "Troka Modu Lee",
                            tint = contentColor
                        )
                    }

                    // Bookmarks list toggle
                    IconButton(
                        onClick = { showBookmarksSheet = true },
                        modifier = Modifier.testTag("reader_bookmarks_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmarks,
                            contentDescription = "Lista Bookmark",
                            tint = contentColor
                        )
                    }

                    // Toggle current page bookmark
                    IconButton(
                        onClick = {
                            viewModel.toggleBookmark(book.id, currentPage + 1)
                        },
                        modifier = Modifier.testTag("toggle_bookmark_btn")
                    ) {
                        Icon(
                            imageVector = if (isCurrentPageBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Rai pájina ne'e",
                            tint = if (isCurrentPageBookmarked) TimorGold else contentColor
                        )
                    }
                }
            }

            // Document Canvas Viewer with Zoom & Pan
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .transformable(state = transformableState),
                contentAlignment = Alignment.Center
            ) {
                if (isLoadingPage && currentBitmap == null) {
                    CircularProgressIndicator(color = TimorRed)
                } else if (currentBitmap != null) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = offset.x
                                translationY = offset.y
                            }
                    ) {
                        Image(
                            bitmap = currentBitmap!!.asImageBitmap(),
                            contentDescription = "Pájina ${currentPage + 1}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.FillWidth,
                            colorFilter = imageColorFilter
                        )
                    }
                }

                // Floating Zoom Quick Controls on side
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                        shadowElevation = 3.dp,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                scale = (scale + 0.35f).coerceAtMost(3.5f)
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.ZoomIn, contentDescription = "Zoom in", tint = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                        shadowElevation = 3.dp,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                scale = (scale - 0.35f).coerceAtLeast(1f)
                                if (scale == 1f) offset = Offset.Zero
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.ZoomOut, contentDescription = "Zoom out", tint = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    if (scale > 1f) {
                        Surface(
                            shape = CircleShape,
                            color = TimorRed.copy(alpha = 0.9f),
                            shadowElevation = 3.dp,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    scale = 1f
                                    offset = Offset.Zero
                                }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.RestartAlt, contentDescription = "Reset zoom", tint = Color.White)
                            }
                        }
                    }
                }
            }

            // Bottom Navigation & Scrubber Bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = topBarColor,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    // Page Scrubber Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${currentPage + 1}",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = contentColor,
                            modifier = Modifier.width(32.dp)
                        )

                        Slider(
                            value = currentPage.toFloat(),
                            onValueChange = { newValue ->
                                currentPage = newValue.toInt().coerceIn(0, (totalPages - 1).coerceAtLeast(0))
                            },
                            valueRange = 0f..(totalPages - 1).coerceAtLeast(1).toFloat(),
                            steps = (totalPages - 2).coerceAtLeast(0),
                            colors = SliderDefaults.colors(
                                thumbColor = TimorRed,
                                activeTrackColor = TimorRed,
                                inactiveTrackColor = contentColor.copy(alpha = 0.2f)
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                                .testTag("reader_page_slider")
                        )

                        Text(
                            text = "$totalPages",
                            style = MaterialTheme.typography.labelMedium,
                            color = contentColor.copy(alpha = 0.7f),
                            modifier = Modifier.width(32.dp)
                        )
                    }

                    // Bottom Navigation Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Previous Page
                        IconButton(
                            onClick = {
                                if (currentPage > 0) {
                                    currentPage--
                                    scale = 1f
                                    offset = Offset.Zero
                                }
                            },
                            enabled = currentPage > 0,
                            modifier = Modifier.testTag("prev_page_btn")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Pájina Uluk",
                                tint = if (currentPage > 0) contentColor else contentColor.copy(alpha = 0.3f)
                            )
                        }

                        // Chapters list trigger
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = contentColor.copy(alpha = 0.08f),
                            modifier = Modifier.clickable { showChaptersSheet = true }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FormatListBulleted,
                                    contentDescription = "Índise Kapítulus",
                                    tint = contentColor,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Índise / Kapítulus",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                    color = contentColor
                                )
                            }
                        }

                        // Next Page
                        IconButton(
                            onClick = {
                                if (currentPage < totalPages - 1) {
                                    currentPage++
                                    scale = 1f
                                    offset = Offset.Zero
                                }
                            },
                            enabled = currentPage < totalPages - 1,
                            modifier = Modifier.testTag("next_page_btn")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Pájina Tuirmai",
                                tint = if (currentPage < totalPages - 1) contentColor else contentColor.copy(alpha = 0.3f)
                            )
                        }
                    }
                }
            }
        }
    }

    // Chapters BottomSheet
    if (showChaptersSheet) {
        ModalBottomSheet(
            onDismissRequest = { showChaptersSheet = false },
            sheetState = rememberModalBottomSheetState()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Kapítulus no Tópikus (${book.grade.displayName})",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(14.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(book.chapters) { chapter ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    currentPage = (chapter.pageNumber - 1).coerceIn(0, totalPages - 1)
                                    showChaptersSheet = false
                                },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (currentPage == chapter.pageNumber - 1) {
                                    TimorRed.copy(alpha = 0.1f)
                                } else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = chapter.title,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                    modifier = Modifier.weight(1f)
                                )
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = TimorRed.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "Páj. ${chapter.pageNumber}",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TimorRed,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Bookmarks BottomSheet
    if (showBookmarksSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBookmarksSheet = false },
            sheetState = rememberModalBottomSheetState()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pájinas Salva (Bookmarks)",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "${bookmarks.size} pájinas",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (bookmarks.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Seidauk iha pájina salva. Tane íkone bookmark atu rai pájina importante.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(bookmarks) { bookmark ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentPage = (bookmark.pageNumber - 1).coerceIn(0, totalPages - 1)
                                        showBookmarksSheet = false
                                    },
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Bookmark,
                                            contentDescription = null,
                                            tint = TimorGold
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "Pájina ${bookmark.pageNumber}",
                                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                        )
                                    }

                                    IconButton(
                                        onClick = { viewModel.removeBookmark(bookmark.id) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Hamoos",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
