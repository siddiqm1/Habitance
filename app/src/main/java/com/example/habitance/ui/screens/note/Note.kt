package com.example.habitance.ui.screens.note

import com.google.firebase.Timestamp

data class Note(
    val id: String = "",
    val activityId: String = "",
    val content: String = "",
    val category: String = "",
    val timestamp: Timestamp = Timestamp.now() // Menambahkan timestamp untuk mencatat waktu pembuatan note
)


