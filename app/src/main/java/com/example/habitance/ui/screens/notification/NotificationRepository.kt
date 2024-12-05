
package com.example.habitance.ui.screens.notification


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NotificationRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun getUserCollection() =
        firestore.collection("users")
            .document(auth.currentUser?.uid ?: "")
            .collection("notifications")

    suspend fun addNotification(notification: Notification) {
        val userCollection = getUserCollection()
        val docRef = userCollection.document(notification.id.ifEmpty { userCollection.document().id })
        docRef.set(notification).await()
    }

    suspend fun getNotifications(): List<Notification> {
        val userCollection = getUserCollection()
        val snapshot = userCollection.get().await()
        return snapshot.toObjects(Notification::class.java)
    }

    suspend fun deleteNotification(id: String) {
        val userCollection = getUserCollection()
        userCollection.document(id).delete().await()
    }
}
