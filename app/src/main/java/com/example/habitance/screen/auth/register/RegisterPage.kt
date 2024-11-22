package com.example.habitance.screen.auth.register

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.example.habitance.R
import com.example.habitance.data.SaveUserDataToFirestore
import com.example.habitance.data.User
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Bottom
import com.example.habitance.ui.theme.fontFamily
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = viewModel()
    val firstname by viewModel.firstname.collectAsState()
    val lastname by viewModel.lastname.collectAsState()
    val username by viewModel.username.collectAsState()
    val dateOfBirth by viewModel.dateOfBirth.collectAsState()
    val gender by viewModel.gender.collectAsState()
    val showDatePicker by viewModel.showDatePicker.collectAsState()
    val buttonColor = Bottom
    val context = LocalContext.current
    val textFieldColor = BackGround2
    val textColor = Color(0xFF1e1e1e)
    val  currentUser = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier
            .background(BackGround)
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.register),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = fontFamily
        )

        var isViewingProfileImage by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) { // first name
            TextField(
                modifier = Modifier.weight(1f),
                value = firstname,
                shape = MaterialTheme.shapes.medium,
                onValueChange = { viewModel.updateFirstname(it) },
                label = {
                    Text(
                        text = "First Name",
                        color = textColor
                    )
                },
                colors = TextFieldDefaults.colors().copy(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedContainerColor = textFieldColor,
                    unfocusedContainerColor = textFieldColor,
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.width(16.dp))
        //lastname
            TextField(
                modifier = Modifier.weight(1f),
                value = lastname,
                shape = MaterialTheme.shapes.medium,
                onValueChange = { viewModel.updateLastname(it) },
                label = { Text(text = "Last Name", color = textColor) },
                colors = TextFieldDefaults.colors().copy(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedContainerColor = textFieldColor,
                    unfocusedContainerColor = textFieldColor,
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
            )
        }
        //usernanme
        TextField(
            value = username,
            shape = MaterialTheme.shapes.medium,
            onValueChange = { viewModel.updateUsername(it) },
            label = { Text(text = "Username", color = textColor) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors().copy(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedContainerColor = textFieldColor,
                unfocusedContainerColor = textFieldColor,
            ),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )
        //kalender
        val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        fun Long.toLocalDate(): LocalDate = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()

        TextField(
            value = dateOfBirth,
            shape = MaterialTheme.shapes.medium,
            onValueChange = { },
            label = { Text("Date of Birth", color = textColor) },
            modifier = Modifier.fillMaxWidth().clickable {viewModel.updateShowDatePicker(true)},
            colors = TextFieldDefaults.colors().copy(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedContainerColor = textFieldColor,
                unfocusedContainerColor = textFieldColor,
            ),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {viewModel.updateShowDatePicker(true)}) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Black)
                }
            }
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {viewModel.updateShowDatePicker(false)},
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formattedDate = millis.toLocalDate().format(formatter)
                            viewModel.updateDateOfBirth(formattedDate)
                        }
                        viewModel.updateShowDatePicker(false)
                    }) {
                        Text(text = "Select")
                    }
                },
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false // Disable mode toggle to ensure only date picking
                )
            }
        }

//gender
        var isExpanded by remember { mutableStateOf(false) }
        val genderOptions = listOf("Male", "Female", "Other")
        val gender by viewModel.gender.collectAsState()

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            TextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                label = { Text("Gender", color = textColor) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = TextFieldDefaults.colors().copy(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedContainerColor = textFieldColor,
                    unfocusedContainerColor = textFieldColor,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .clickable { isExpanded = true }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                genderOptions.forEach { genderOption ->
                    DropdownMenuItem(
                        text = { Text(genderOption, color = Color.Black) },
                        onClick = {
                            viewModel.updateGender(genderOption)
                            isExpanded = false
                        }
                    )
                }
            }
        }




        Button(
            onClick = {
                val userId = currentUser?.uid
                if (userId != null) {
                    SaveUserDataToFirestore(
                        user = User
                            (
                            id = Firebase.auth.currentUser!!.uid,
                            name = firstname,
                            email = currentUser.email.toString(),
                            country = "Indonesia",
                            gender = gender
                        ),
                        context = context
                    )
                    { isSuccess ->
                        if (isSuccess) {
                            Log.d("FirestoreSave", "Data successfully saved to Firestore")
                        } else {
                            Log.e("FirestoreSave", "Failed to save data to Firestore")
                        }
                    }
                }
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text(text = "Register", color = Color.White)
        }

        TextButton(
            onClick = {
                FirebaseAuth.getInstance().signOut()
                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back to Login", color = Color.Black)
        }

    }
}



