package com.example.habitance

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun kasir() {
    val db = Firebase.firestore
 Text("hai")
}

@Preview (showBackground = true)
@Composable
fun kasirPreview(){
    kasir()
}