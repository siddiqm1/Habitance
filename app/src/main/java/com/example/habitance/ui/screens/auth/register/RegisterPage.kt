package com.example.habitance.ui.screens.auth.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habitance.R
import com.example.habitance.data.SaveUserDataToFirestore
import com.example.habitance.data.User
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Bottom
import com.example.habitance.ui.theme.TextLogo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: RegisterViewModel = viewModel()
    val firstname by viewModel.firstname.collectAsState()
    val lastname by viewModel.lastname.collectAsState()
    val username by viewModel.username.collectAsState()
    val dateOfBirth by viewModel.dateOfBirth.collectAsState()
    val showDatePicker by viewModel.showDatePicker.collectAsState()
    val gender by viewModel.gender.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val textFieldColor = BackGround2
    val textColor = Color(0xFF1e1e1e)
    val context = LocalContext.current
    val currentUser = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 28.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Black,
            color = TextLogo,
            style = TextStyle(
                shadow = Shadow(
                    color = TextLogo,
                    offset = Offset(2f, 2f),

                    )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Baris nama depan dan belakang
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = firstname,
                        onValueChange = { viewModel.updateFirstname(it) },
                        label = { Text("First Name", color = textColor,fontSize = 13.sp) },
                        modifier = Modifier.weight(1f),
                        shape = MaterialTheme.shapes.medium,
                        colors = TextFieldDefaults.colors().copy(
                            focusedContainerColor = textFieldColor,
                            unfocusedContainerColor = textFieldColor,
                            cursorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = LocalTextStyle.current.copy(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    TextField(
                        value = lastname,
                        onValueChange = { viewModel.updateLastname(it) },
                        label = { Text("Last Name", color = textColor,fontSize = 13.sp) },
                        modifier = Modifier.weight(1f),
                        shape = MaterialTheme.shapes.medium,
                        colors = TextFieldDefaults.colors().copy(
                            focusedContainerColor = textFieldColor,
                            unfocusedContainerColor = textFieldColor,
                            cursorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = LocalTextStyle.current.copy(color = Color.Black)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Username
                TextField(
                    value = username,
                    onValueChange = { viewModel.updateUsername(it) },
                    label = { Text("Username", color = textColor,fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = textFieldColor,
                        unfocusedContainerColor = textFieldColor,
                        cursorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(color = Color.Black)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tanggal lahir
                val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                TextField(
                    value = dateOfBirth,
                    onValueChange = {},
                    label = { Text("Date of Birth", color = textColor,fontSize = 13.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.updateShowDatePicker(true) },
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = textFieldColor,
                        unfocusedContainerColor = textFieldColor,
                        cursorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),

                    textStyle = LocalTextStyle.current.copy(color = Color.Black),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { viewModel.updateShowDatePicker(true) }) {
                            Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Black)
                        }
                    }
                )

                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { viewModel.updateShowDatePicker(false) },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let { millis ->
                                    val formattedDate = Instant.ofEpochMilli(millis)
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate()
                                        .format(formatter)
                                    viewModel.updateDateOfBirth(formattedDate)
                                }
                                viewModel.updateShowDatePicker(false)
                            }) {
                                Text("Select")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState, showModeToggle = false)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Gender
                val expanded = remember { mutableStateOf(false) }
                val genderOptions = listOf("Male", "Female", "Other")

                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = { expanded.value = !expanded.value }
                ) {
                    TextField(
                        value = gender,
                        onValueChange = {},
                        label = { Text("Gender", color = textColor,fontSize = 13.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { expanded.value = !expanded.value }) {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                            }
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = textFieldColor,
                            unfocusedContainerColor = textFieldColor,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        genderOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    viewModel.updateGender(option)
                                    expanded.value = false
                                }
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Tombol register
                Button(
                    onClick = {
                        if (firstname.isBlank() || lastname.isBlank() || username.isBlank()) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        } else {
                            isLoading = true
                            val userId = currentUser?.uid
                            if (userId != null) {
                                SaveUserDataToFirestore(
                                user = User
                                    (
                                    id = Firebase.auth.currentUser!!.uid,
                                    name = username,
                                    email = currentUser.email.toString(),
                                    country = "Indonesia",
                                    dateofbirth = dateOfBirth,
                                    gender = gender
                                ),
                                context = context
                            )
                            { isSuccess ->
                                isLoading = false
                                if (isSuccess) {
                                    Toast.makeText(context, "Register successful!", Toast.LENGTH_SHORT).show()
                                    navController.navigate("home")
                                } else {
                                    Toast.makeText(context, "Failed to register. Try again!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(),
                    shape = RoundedCornerShape(16.dp),
                    enabled = !isLoading
                ) {
                    Text(
                        text = if (isLoading) "Loading..." else "Register",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

                TextButton(
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("login")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Back to Login",
                        color =com.example.habitance.ui.theme.BottomText
                    )
                }

            }
        }
    }
}



