package com.example.habitance.ui.screens.notification

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotificationViewModel(private val repository: NotificationRepository) : ViewModel() {

    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            _notifications.value = repository.getNotifications()
        }
    }

    fun addNotification(notification: Notification) {
        viewModelScope.launch {
            repository.addNotification(notification)
            loadNotifications()
        }
    }

    fun deleteNotification(id: String, context: Context) {
        viewModelScope.launch {
            try {
                repository.deleteNotification(id)
                loadNotifications()
                Toast.makeText(context, "Notifikasi berhasil dihapus", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Gagal menghapus notifikasi: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }



    fun updateNotificationState(id: String, isEnabled: Boolean) {
        viewModelScope.launch {
            repository.updateNotificationState(id, isEnabled)
            loadNotifications()
        }
    }
}




