package com.example.habitance.activity

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habitance.AuthState
import com.example.habitance.AuthViewModel
import com.example.habitance.R


@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .background(Color(0xFFC4DAD2)), // Sesuaikan warna latar belakang
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Habitance",
            fontSize = 28.sp,
            fontFamily = fontFamily,  // Gunakan FontFamily yang sudah didefinisikan
            fontWeight = FontWeight.Bold,  // Pilih gaya bold dari FontFamily
            color = Color(0xFF1A5D44)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Card for Signup Form
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp) // Perbaikan Elevas
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create Your Account",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Username ID",color = Color.Gray) },
                    placeholder = { Text("Enter your username") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password",color = Color.Gray) },
                    placeholder = { Text("Password") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol sigup
                Button(
                    onClick = { authViewModel.signup(email, password) },
                    enabled = authState.value != AuthState.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A5D44)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "Create Account", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Already have an account? Login
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Already have an account?")
                    TextButton(
                        onClick = { navController.navigate("login") },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(text = "Login", fontSize = 14.sp ,color = Color(0xFF1A5D44))
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Login with Third Party
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = " or Continue with ",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Gray
                )
                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Google and Facebook Buttons
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { /* Google Login */ }) {
                    Text(text = "Google", color = Color.Gray)
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = { /* Facebook Login */ }) {
                    Text(text = "Facebook", color = Color.Gray)
                }
            }
        }
    }
}