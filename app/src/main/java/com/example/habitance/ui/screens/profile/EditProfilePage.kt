package com.example.habitance.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Bottom
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfilePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel()
) {
    // State dari ViewModel
    val dateOfBirth by profileViewModel.dateofbirth.collectAsState()
    var updatedName by remember { mutableStateOf(profileViewModel.name.value) }
    val gender by profileViewModel.gender.collectAsState()
    val showDatePicker by profileViewModel.showDatePicker.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
       TextField(
           value = updatedName,
           onValueChange = { updatedName = it },
           label = { Text("Name", color = Color.Gray, fontSize = 13.sp) },
           modifier = Modifier.fillMaxWidth(),
           shape = MaterialTheme.shapes.medium

       )
        // Tanggal Lahir (Date Picker)
        Spacer(modifier = Modifier.height(16.dp))
        val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
        val formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy")

        TextField(
            value = dateOfBirth,
            onValueChange = {profileViewModel.updateDateOfBirth(it)},
            label = { Text("Date of Birth", color = Color.Gray, fontSize = 13.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { profileViewModel.updateShowDatePicker(true) },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BackGround2,
                unfocusedContainerColor = BackGround2,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { profileViewModel.updateShowDatePicker(true) }) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Black)
                }
            }
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { profileViewModel.updateShowDatePicker(false) },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formattedDate = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .format(formatter)
                            profileViewModel.updateDateOfBirth(formattedDate)
                        }
                        profileViewModel.updateShowDatePicker(false)
                    }) {
                        Text("Select")
                    }
                }
            ) {
                DatePicker(state = datePickerState, showModeToggle = false)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gender (Dropdown Menu)
        val expanded = remember { mutableStateOf(false) }
        val genderOptions = listOf("Male", "Female", "Other")

        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            TextField(
                value = gender,
                onValueChange = {},
                label = { Text("Gender", color = Color.Gray, fontSize = 13.sp) },
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
                    focusedContainerColor = BackGround2,
                    unfocusedContainerColor = BackGround2,
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
                            profileViewModel.updateGender(option) // Perbarui gender
                            expanded.value = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Simpan
        Button(
            onClick = {
                profileViewModel.updateName(updatedName)

                // Pastikan data dari ViewModel diteruskan ke fungsi updateProfile
                profileViewModel.updateProfile(
                    name = updatedName,
                    gender = profileViewModel.gender.value,
                    country = profileViewModel.country.value,
                    dateOfBirth = profileViewModel.dateofbirth.value
                )
                Toast.makeText(context, "Profile Updated!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Bottom),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Save Changes", color = Color.White, fontSize = 18.sp)
        }
    }
    }
