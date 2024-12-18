package com.example.habitance.ui.screens.note

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habitance.R
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.Border2
import com.example.habitance.ui.theme.TextDark
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AddNote(navController: NavHostController, activityName: String, activityId: String) {
    var noteContent by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = {
            if (showSnackbar) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    containerColor = TextDark,
                    contentColor = Color.White,
                    action = {
                        TextButton(onClick = { showSnackbar = false }) {
                            Text("Tutup", color = Color.White)
                        }
                    }
                ) {
                    Text(snackbarMessage)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGround)
                .padding(paddingValues)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "Back",
                        modifier = Modifier.size(25.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Note Input Card
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = "Tambah Note",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Title (Activity Name)
                    Text(
                        text = "Aktivitas: $activityName",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Border
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Note Input
                    TextField(
                        value = noteContent,
                        onValueChange = { noteContent = it },
                        label = { Text("Isi Note") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, TextDark, shape = RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Border2,
                            unfocusedContainerColor = Border2,
                            cursorColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Save Button
                    Button(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            if (noteContent.isNotBlank() && currentUser != null) {
                                // Create a new note
                                val note = hashMapOf(
                                    "activityId" to activityId,
                                    "activityName" to activityName,
                                    "content" to noteContent,
                                    "timestamp" to com.google.firebase.Timestamp.now()
                                )

                                // Add note to Firestore
                                firestore.collection("users")
                                    .document(currentUser.uid)
                                    .collection("notes")
                                    .add(note)
                                    .addOnSuccessListener {
                                        snackbarMessage = "Note berhasil ditambahkan!"
                                        showSnackbar = true
                                        navController.popBackStack() // Return to previous screen
                                    }
                                    .addOnFailureListener { e ->
                                        snackbarMessage = "Gagal menambahkan note: ${e.message}"
                                        showSnackbar = true
                                        Log.e("Firestore", "Error adding note", e)
                                    }
                            } else {
                                snackbarMessage = "Isi note tidak boleh kosong!"
                                showSnackbar = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = TextDark)
                    ) {
                        Text("Simpan", color = Color.White)
                    }
                }
            }
        }
    }
}



