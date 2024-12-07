package com.example.habitance.ui.screens.notification

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.habitance.ui.screens.notification.utils.NotificationWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

fun scheduleNotification(context: Context, notification: Notification) {
    val currentTime = System.currentTimeMillis()
    val notificationTime = convertTimeToMillis(notification.time)

    val delay = if (notificationTime < currentTime) {
        notificationTime + 24 * 60 * 60 * 1000 - currentTime
    } else {
        notificationTime - currentTime
    }

    val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
        .setInputData(
            workDataOf(
                "title" to notification.title,
                "description" to notification.description
            )
        )
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}

fun convertTimeToMillis(time: String): Long {
    val timeParts = time.split(":").map { it.toInt() }
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, timeParts[0])
        set(Calendar.MINUTE, timeParts[1])
        set(Calendar.SECOND, 0)
    }
    return calendar.timeInMillis
}
