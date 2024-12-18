package com.example.habitance.ui.screens.add_activity

import com.example.habitance.ui.screens.note.Note
import com.google.firebase.Timestamp


data class Activity(
    val id : String = "",
    val name: String = "",
    val unit: String = "",
    val progress: Map<String, Int> = emptyMap(),
    val target: Int = 0,
    val category: CategoryActivity = CategoryActivity.Baik,
    val periode : String = "",
    val start: Timestamp = Timestamp.now(),
    val end: Timestamp = Timestamp.now(),
    val notes: List<Note> = emptyList()
)

enum class CategoryActivity {
    Baik, Buruk
}

