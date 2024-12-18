package com.example.habitance.ui.screens.note

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habitance.ui.components.CardNoteActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListNote(
    navController: NavController,
    activityId: String,
    activityName: String,
    viewModel: NoteViewModel
) {
    val notes by viewModel.notes.collectAsState()

    LaunchedEffect(activityId) {
        Log.d("Firestore", "Fetching notes for activityId: $activityId")
        viewModel.fetchNotesByActivity(activityId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Daftar Note untuk: $activityName") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (notes.isEmpty()) {
                item {
                    Log.d("Firestore", "No notes available")
                    Text(
                        text = "Belum ada catatan untuk aktivitas ini.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                Log.d("Firestore", "Displaying ${notes.size} notes")
                items(notes) { note ->
                    CardNoteActivity(note, activityName)
                }
            }
        }
    }
}











