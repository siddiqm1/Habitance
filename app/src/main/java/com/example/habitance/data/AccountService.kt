package com.example.habitance.data

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AccountService {
    private val auth = Firebase.auth
    val currentUser = auth.currentUser
    val currentUserUid = currentUser?.uid
}