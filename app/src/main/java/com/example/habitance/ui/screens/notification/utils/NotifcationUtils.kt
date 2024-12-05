package com.example.habitance.ui.screens.notification.utils


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun showNotification(context: Context, title: String, description: String) {
    val channelId = "habitance_channel"
    val channelName = "Habitance Notifications"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }

    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle(title)
        .setContentText(description)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()

    NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
}
