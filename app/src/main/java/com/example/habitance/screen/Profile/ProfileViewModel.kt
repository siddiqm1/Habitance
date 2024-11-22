package com.example.habitance.screen.profile

import androidx.lifecycle.ViewModel
import coil3.Uri
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val _firstname = MutableStateFlow("")
    val firstname: StateFlow<String> get() = _firstname

    private val _lastname = MutableStateFlow("")
    val lastname: StateFlow<String> get() = _lastname

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth: StateFlow<String> get() = _dateOfBirth

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> get() = _imageUri

    private val _imageUrl = MutableStateFlow("")
    val imageUrl: StateFlow<String> get() = _imageUrl

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _showDatePicker = MutableStateFlow(false)
    val showDatePicker: StateFlow<Boolean> get() = _showDatePicker

    private val userId = FirebaseAuth.getInstance().currentUser?.uid

//    init {
//        loadProfileData()
//    }

}