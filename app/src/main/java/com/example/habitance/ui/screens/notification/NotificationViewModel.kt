package com.example.habitance.ui.screens.notification

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

    fun deleteNotification(id: String) {
        viewModelScope.launch {
            repository.deleteNotification(id)
            loadNotifications()
        }
    }
}
