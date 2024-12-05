package com.example.habitance.ui.screens.notification

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NotificationRepository {

    val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("notifications")

    // Fungsi untuk menyimpan notifikasi
    suspend fun addNotification(notification: NotificationModel) {
        val docRef = collection.document(notification.id.ifEmpty { collection.document().id })
        docRef.set(notification).await()
    }

    // Fungsi untuk mengambil semua notifikasi
    suspend fun getNotifications(): List<NotificationModel> {
        val snapshot = collection.get().await()
        return snapshot.toObjects(NotificationModel::class.java)
    }

    // Fungsi untuk menghapus notifikasi
    suspend fun deleteNotification(id: String) {
        collection.document(id).delete().await()
    }
}
