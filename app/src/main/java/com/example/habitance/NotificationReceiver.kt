package com.example.habitance


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.habitance.ui.screens.notification.utils.showNotification

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Default Title"
        val description = intent.getStringExtra("description") ?: "Default Description"
        showNotification(context, title, description)
    }
}
