//package com.example.habitance.ui.screens.notification
//
//import android.Manifest
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.content.pm.PackageManager
//import android.os.Build
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationCompat
//import andzroidx.core.app.NotificationManagerCompat
//import com.example.habitance.R
//*
//fun showNotification(context: Context, title: String, description: String) {
//    val channelId = "habitance_channel"
//    val channelName = "Habitance Notifications"
//
//    // Membuat NotificationChannel jika diperlukan (Android O+)
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = NotificationChannel(channelId, channelName, importance)
//        val notificationManager = context.getSystemService(NotificationManager::class.java)
//        notificationManager.createNotificationChannel(channel)
//    }
//
//    // Memeriksa izin POST_NOTIFICATIONS (Android 13+)
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // Jika izin belum diberikan, keluar dari fungsi
//            return
//        }
//    }
//
//    // Membuat notifikasi
//    val notification = NotificationCompat.Builder(context, channelId)
//        .setSmallIcon(R.drawable.logo) // Ganti dengan ikon kustom jika diperlukan
//        .setContentTitle(title)
//        .setContentText(description)
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        .build()
//
//    // Menampilkan notifikasi
////    NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
//}
