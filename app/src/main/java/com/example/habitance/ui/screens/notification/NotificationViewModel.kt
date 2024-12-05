package com.example.habitance.ui.screens.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotificationViewModel(private val repository: NotificationRepository) : ViewModel() {

    private val _notifications = MutableStateFlow<List<NotificationModel>>(emptyList())
    val notifications: StateFlow<List<NotificationModel>> = _notifications

    // Menambahkan notifikasi
    fun addNotification(notification: NotificationModel) {
        viewModelScope.launch {
            val newNotification = if (notification.id.isEmpty()) {
                notification.copy(id = generateId())
            } else {
                notification
            }
            repository.addNotification(newNotification)
            loadNotifications()
        }
    }

    private fun generateId(): String {
        return repository.firestore.collection("notifications").document().id
    }

    // Menghapus notifikasi
    fun deleteNotification(id: String) {
        viewModelScope.launch {
            repository.deleteNotification(id)
            loadNotifications()
        }
    }

    // Memuat semua notifikasi
    private fun loadNotifications() {
        viewModelScope.launch {
            _notifications.value = repository.getNotifications()
        }
    }
}