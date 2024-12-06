package com.example.habitance.ui.screens.notification

data class Notification(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val time: String = "", // Format: "HH:mm"
    val isEnabled: Boolean = false // Status aktif/mati notifikasi
)
