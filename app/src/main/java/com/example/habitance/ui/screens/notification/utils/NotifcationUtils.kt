package com.example.habitance.ui.screens.notification.utils


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.habitance.MainActivity
import com.example.habitance.R


fun showNotification(context: Context, title: String, description: String) {
    val channelId = "habitance_channel"
    val channelName = "Habitance Notifications"

    // Membuat NotificationChannel untuk Android 8.0+ (Oreo)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    // Minta izin untuk Android 13+ (TIRAMISU)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }

    // Intent untuk membuka MainActivity
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Bangun notifikasi
    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.logo) // Ganti dengan ikon Anda
        .setContentTitle(title)
        .setContentText(description)
        .setContentIntent(pendingIntent) // Tambahkan PendingIntent di sini
        .setAutoCancel(true) // Hapus notifikasi setelah ditekan
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()

    // Tampilkan notifikasi
    NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
}
