package com.example.habitance.ui.screens.note

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    // Fungsi untuk mengambil note berdasarkan activityId
    fun fetchNotesByActivity(activityId: String) {
        Log.d("Firestore", "Fetching notes for activityId: $activityId")

        if (activityId.isEmpty()) {
            Log.e("Firestore", "Activity ID is empty. Cannot fetch notes.")
            return
        }

        currentUser?.let { user ->
            firestore.collection("users")
                .document(user.uid)
                .collection("notes")
                .whereEqualTo("activityId", activityId) // Filter berdasarkan activityId
                .get()
                .addOnSuccessListener { documents ->
                    val fetchedNotes = documents.map { document ->
                        Note(
                            id = document.id,
                            activityId = document.getString("activityId") ?: "",
                            content = document.getString("content") ?: "",
                            timestamp = document.getTimestamp("timestamp") ?: Timestamp.now()
                        )
                    }
                    Log.d("Firestore", "Total notes fetched: ${fetchedNotes.size}")
                    _notes.value = fetchedNotes
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error fetching notes", e)
                }
        } ?: Log.e("Firestore", "User not logged in.")
    }
}

