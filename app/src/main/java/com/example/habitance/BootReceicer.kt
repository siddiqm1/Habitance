package com.example.habitance


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.habitance.ui.screens.notification.NotificationRepository
import com.example.habitance.ui.screens.notification.ScheduleNotificationWithAlarmManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val repository = NotificationRepository() // Inisialisasi repositori
            CoroutineScope(Dispatchers.IO).launch {
                val notifications = repository.getNotifications()
                for (notification in notifications) {
                    ScheduleNotificationWithAlarmManager(context, notification,)
                }
            }
        }
    }
}

