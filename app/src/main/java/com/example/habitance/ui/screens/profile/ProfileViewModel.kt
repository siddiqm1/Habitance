package com.example.habitance.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _imageUrl = MutableStateFlow("")
    val imageUrl: StateFlow<String> get() = _imageUrl

    private val _showDatePicker = MutableStateFlow(false)
    val showDatePicker: StateFlow<Boolean> get() = _showDatePicker

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> get() = _name

    private val _country = MutableStateFlow("")
    val country: StateFlow<String> get() = _country

    private val _dateofbirth = MutableStateFlow("")
    val dateofbirth: StateFlow<String> get() = _dateofbirth


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> get() = _gender

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun updateDateOfBirth(newDate: String) {
        _dateofbirth.value = newDate
    }

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateGender(newGender: String) {
        _gender.value = newGender
    }

    fun updateShowDatePicker(show: Boolean) {
        _showDatePicker.value = show
    }



private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    init {
        loadProfileData()
    }
    fun updateProfile(name: String, gender: String, country: String, dateOfBirth: String) {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            val userId = user.uid
            val updates = mapOf(
                "name" to name,
                "gender" to gender,
                "country" to country,
                "dateofbirth" to dateOfBirth
            )

            firestore.collection("users").document(userId).update(updates)
                .addOnSuccessListener {
                    _name.value = name
                    _gender.value = gender
                    _country.value = country
                    _dateofbirth.value = dateOfBirth
                    println("Profile updated successfully") // Debugging
                }
                .addOnFailureListener { error ->
                    println("Failed to update profile: ${error.message}") // Debugging
                }
        }
    }


    private fun loadProfileData() {
        viewModelScope.launch {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                _isLoading.value = true
                val userId = currentUser.uid

                firestore.collection("users").document(userId).get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            _name.value = document.getString("name") ?: "Unknown"
                            _email.value = document.getString("email") ?: "Email not available"
                            _gender.value = document.getString("gender") ?: "Not specified"
                            _imageUrl.value = currentUser.photoUrl?.toString() ?: ""
                            _country.value = document.getString("country") ?: "Country not available"
                            _dateofbirth.value = document.getString("dateofbirth") ?: "Date of birth not available"
                            println("Profile data loaded successfully") // Debugging
                        } else {
                            println("Document not found") // Debugging
                            _name.value = "Data not found"
                        }
                        _isLoading.value = false
                    }
                    .addOnFailureListener { error ->
                        println("Error loading data: ${error.message}") // Debugging
                        _name.value = "Error loading data"
                        _isLoading.value = false
                    }

                    .addOnFailureListener {
                        _name.value = "Error loading data"
                        _isLoading.value = false
                    }
            } else {
                _name.value = "User not logged in"
            }
        }
    }
}
