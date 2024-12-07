package com.example.habitance.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habitance.R
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.Border2
import com.example.habitance.ui.theme.Bottom
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.fontFamily
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
    val context = LocalContext.current
    val dateOfBirth by profileViewModel.dateofbirth.collectAsState()
    var name by remember { mutableStateOf(profileViewModel.name.value) }
    val gender by profileViewModel.gender.collectAsState()
    val showDatePicker by profileViewModel.showDatePicker.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackGround2)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(72.dp)
                    )
                }
                Spacer(modifier = Modifier.width(48.dp)) // Placeholder untuk menjaga kesimetrisan
            }
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BackGround2)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = BackGround2),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Border2)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                        Text(
                            text = "Changes Profile",
                            fontSize = 24.sp,
                            fontFamily = fontFamily,
                            color = TextDark,
                            fontWeight = FontWeight(600)
                        )
                    // Input Nama
                    TextField(
                        value = name,
                        onValueChange = {
                            name = it
                            profileViewModel.updateName(it)
                        },
                        label = { Text("Name", fontSize = 14.sp, color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp), // Membulatkan Outline
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    // Input Tanggal Lahir
                    val datePickerState =
                        rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
                    val formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy")

                    TextField(
                        value = dateOfBirth,
                        onValueChange = {},
                        label = { Text("Date of Birth", fontSize = 14.sp, color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { profileViewModel.updateShowDatePicker(true) },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { profileViewModel.updateShowDatePicker(true) }) {
                                Icon(
                                    Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        },
                        shape = RoundedCornerShape(16.dp), // Membulatkan Outline
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
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

                    val expanded = remember { mutableStateOf(false) }
                    val genderOptions = listOf("Male", "Female", "Other")

                    ExposedDropdownMenuBox(
                        expanded = expanded.value,
                        onExpandedChange = { expanded.value = !expanded.value }
                    ) {
                        TextField(
                            value = gender,
                            onValueChange = {},
                            label = { Text("Gender", fontSize = 14.sp, color = Color.Gray) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = { expanded.value = !expanded.value }) {
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Dropdown Icon"
                                    )
                                }
                            },
                            shape = RoundedCornerShape(16.dp), // Membulatkan Outline
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                cursorColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
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
                                        profileViewModel.updateGender(option)
                                        expanded.value = false
                                    }
                                )
                            }
                        }
                    }

                    // Tambahkan tombol Save Changes di dalam Card
                    Spacer(modifier = Modifier.height(16.dp)) // Jarak antara konten dan tombol
                    Button(
                        onClick = {
                            profileViewModel.updateProfile(
                                name = profileViewModel.name.value,
                                gender = profileViewModel.gender.value,
                                country = profileViewModel.country.value,
                                dateOfBirth = profileViewModel.dateofbirth.value
                            )
                            Toast.makeText(context, "Profile Updated!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Save Changes", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
