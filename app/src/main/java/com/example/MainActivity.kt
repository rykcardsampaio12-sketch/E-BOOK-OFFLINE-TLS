package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.P2PDialog
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PdfReaderScreen
import com.example.ui.screens.SubjectDetailScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.EduLibraryViewModel
import com.example.ui.viewmodel.UiEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EduLibraryApp()
                }
            }
        }
    }
}

@Composable
fun EduLibraryApp(
    viewModel: EduLibraryViewModel = viewModel()
) {
    val context = LocalContext.current
    val activeBook by viewModel.activeReadingBook.collectAsState()
    val activeFile by viewModel.activeReadingFile.collectAsState()
    val selectedSubject by viewModel.selectedSubject.collectAsState()
    val selectedGrade by viewModel.selectedGrade.collectAsState()
    val p2pBook by viewModel.p2pSharingBook.collectAsState()

    // Handle toast messages
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is UiEvent.NavigateToReader -> {
                    // State automatically handles navigation
                }
            }
        }
    }

    // Handle back button hierarchically
    BackHandler(enabled = activeBook != null || selectedSubject != null) {
        if (activeBook != null) {
            viewModel.closeReader()
        } else if (selectedSubject != null) {
            viewModel.selectSubject(null)
        }
    }

    // Screen navigation
    when {
        activeBook != null && activeFile != null -> {
            PdfReaderScreen(
                book = activeBook!!,
                file = activeFile!!,
                viewModel = viewModel,
                onBack = { viewModel.closeReader() }
            )
        }
        selectedSubject != null -> {
            SubjectDetailScreen(
                subject = selectedSubject!!,
                grade = selectedGrade,
                viewModel = viewModel,
                onBack = { viewModel.selectSubject(null) }
            )
        }
        else -> {
            HomeScreen(
                viewModel = viewModel,
                onSelectSubject = { subject ->
                    viewModel.selectSubject(subject)
                }
            )
        }
    }

    // P2P Sharing Modal Dialog
    if (p2pBook != null) {
        P2PDialog(
            book = p2pBook!!,
            p2pManager = viewModel.p2pManager,
            onDismiss = { viewModel.closeP2PShare() },
            onSendToPeer = { peer ->
                viewModel.sendBookToPeer(peer)
            }
        )
    }
}

