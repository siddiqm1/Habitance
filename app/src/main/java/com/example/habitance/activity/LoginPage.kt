package com.example.habitance.activity
//import android.graphics.fonts.Font
//import android.graphics.fonts.FontFamily
import androidx.compose.material3.IconButton
import androidx.compose.foundation.Image
import android.widget.Toast
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
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily


import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habitance.AuthState
import com.example.habitance.AuthViewModel
import com.example.habitance.R

val fontFamily = FontFamily(
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_thin, FontWeight.Thin),
    Font(R.font.lexend_black, FontWeight.Black),
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
    Font(R.font.lexend_extralight, FontWeight.ExtraLight),
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
)


@Composable

fun LoginPage(modifier : Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel){

    var email by remember {
        mutableStateOf("") }
    var password by remember {
        mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }


    Column(
        modifier
            .fillMaxSize()
            .background(Color(0xFFC4DAD2)), // Sesuaikan warna latar belakang
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
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



        Spacer(modifier = Modifier.height(24.dp))

        // Card untuk Konten Login
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
                    text = "Login",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Username ID") },
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
                    label = { Text("Password") },
                    placeholder = { Text("Password") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Login
                Button(
                    onClick = { authViewModel.login(email, password) },
                    enabled = authState.value != AuthState.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A5D44)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "LOGIN", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Text untuk Sign Up
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "Don't have an account?")
                    TextButton(
                        onClick = { navController.navigate("signup") },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(text = "Sign up", color = Color(0xFF1A5D44))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //Login dengan Third Party
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
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { /* Google */ }) {
                        Text(text = "Google", color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { /* Facebook */ }) {
                        Text(text = "Facebook", color = Color.Gray)
                    }
                }


            }
        }
    }
}