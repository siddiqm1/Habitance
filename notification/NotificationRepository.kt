
package com.example.habitance.ui.screens.notification


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NotificationRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val notificationList = mutableListOf<Notification>()

    private fun getUserCollection(): CollectionReference? {
        val currentUser = auth.currentUser
        return currentUser?.let {
            firestore.collection("users")
                .document(it.uid)
                .collection("notifications")
        }
    }


    suspend fun addNotification(notification: Notification) {
        val userCollection = getUserCollection()
            ?: throw IllegalStateException("Tidak dapat menambahkan notifikasi. Pengguna belum login.")

        val docRef =
            userCollection.document(notification.id.ifEmpty { userCollection.document().id })
        docRef.set(notification).await()
    }

    suspend fun getNotifications(): List<Notification> {
        val userCollection = getUserCollection()
        if (userCollection == null) {
            // Kembalikan daftar kosong jika pengguna tidak login
            return emptyList()
        }

        val snapshot = userCollection.get().await()
        return snapshot.toObjects(Notification::class.java)
    }



    suspend fun deleteNotification(id: String) {
        val userCollection = getUserCollection()
        if (userCollection == null) {
            // Hindari exception, cukup abaikan operasi jika pengguna tidak login
            return
        }

        userCollection.document(id).delete().await()
    }



    fun updateNotificationState(id: String, isEnabled: Boolean) {
        notificationList.find { it.id == id }?.let {
            val index = notificationList.indexOf(it)
            notificationList[index] = it.copy(isEnabled = isEnabled)
        }
    }
}

