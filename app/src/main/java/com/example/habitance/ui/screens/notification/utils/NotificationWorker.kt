package com.example.habitance.ui.screens.notification.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val title = inputData.getString("title") ?: "No Title"
        val description = inputData.getString("description") ?: "No Description"
        showNotification(applicationContext, title, description)
        return Result.success()
    }
}

