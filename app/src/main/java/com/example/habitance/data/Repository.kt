package com.example.habitance.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class Repository {
    private val db = Firebase.firestore
    private val usersCollection = db.collection("users")
    private fun activityCollection(userId: String): CollectionReference {
        return usersCollection.document(userId).collection("activities")
    }

    suspend fun updateActivityProgress(userId: String, activityId: String, progress: Map<String, Int>) {
        activityCollection(userId).document(activityId).update("progress", progress).await()
    }
}