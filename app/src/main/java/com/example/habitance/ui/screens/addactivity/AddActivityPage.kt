package com.example.habitance.ui.screens.addactivity

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.habitance.R
import com.example.habitance.navbar.BottomBarScreen
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.components.CategorySelection
import com.example.habitance.ui.components.DatePicker
import com.example.habitance.ui.components.TextFieldActivity
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.fontFamily
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AddActivity(navController: NavController) {
    var activity by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var target by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    // Validasi apakah semua field sudah terisi
    val isFormValid = activity.isNotBlank() &&
            category.isNotBlank() &&
            unit.isNotBlank() &&
            target.isNotBlank() &&
            startDate.isNotBlank() &&
            endDate.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "back button",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navController.navigate(BottomBarScreen.Activity.route)
                        }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-30).dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(72.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Border),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .width(378.dp)
                    .height(720.dp)
                    .background(BackGround2)
                    .padding(vertical = 24.dp, horizontal = 24.dp)
            ) {

                Text(
                    text = "ADD ACTIVITY",
                    fontSize = 26.sp,
                    fontFamily = fontFamily,
                    color = TextDark,
                    fontWeight = FontWeight(600)
                )

                TextFieldActivity("Nama Aktivitas", activity, onValueChange = { activity = it })

                CategorySelection(onCategorySelected = { category = it })

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldActivity("Satuan", unit, onValueChange = { unit = it })

                TextFieldActivity("Target", target, onValueChange = { target = it })

                DatePicker { start, end ->
                    if (start != null) {
                        startDate = start
                    }
                    if (end != null) {
                        endDate = end
                    }
                }
                Button(
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(TextDark),
                    enabled = isFormValid, // Tombol akan diaktifkan hanya jika semua field terisi
                    onClick = {
                        val firestore = FirebaseFirestore.getInstance()
                        val currentUser = FirebaseAuth.getInstance().currentUser

                        if (currentUser != null) {
                            val newActivity = Activity(
                                name = activity,
                                unit = unit,
                                target = target,
                                category = category,
                                start = startDate,
                                end = endDate
                            )

                            firestore.collection("users")
                                .document(currentUser.uid)
                                .collection("activities")
                                .add(newActivity)
                                .addOnSuccessListener {
                                    navController.navigate(Screen.ActivityListEmpty.route)
                                }
                                .addOnFailureListener { e ->
                                    // Tangani kesalahan
                                    Log.e("Firestore", "Error adding document", e)
                                }
                        }
                    }
                ) {
                    Text(
                        text = "Simpan",
                        modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp),
                        fontFamily = fontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddActivityPreview() {
    AddActivity(navController = rememberNavController())
}


