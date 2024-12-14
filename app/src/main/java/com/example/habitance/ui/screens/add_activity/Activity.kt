package com.example.habitance.ui.screens.add_activity

import com.google.firebase.Timestamp


data class Activity(
    val id : String = "",
    val name: String = "",
    val unit: String = "",
    val progress: List<Int> = emptyList(),
    val target: Int = 0,
    val category: CategoryActivity = CategoryActivity.Baik,
    val periode : String = "",
    val start: Timestamp = Timestamp.now(),
    val end: Timestamp = Timestamp.now()
)

enum class CategoryActivity {
    Baik, Buruk
}

data class ActivityTest(
    val id : String = "",
    val name: String = "",
    val unit: String = "",
    val progress: Int = 0,
    val target: Int = 0,
    val category: CategoryActivity = CategoryActivity.Baik,
    val periode : String = "",
    val start: Timestamp = Timestamp.now(),
    val end: Timestamp = Timestamp.now()
)