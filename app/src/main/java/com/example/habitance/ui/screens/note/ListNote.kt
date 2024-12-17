package com.example.habitance.ui.screens.note

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.habitance.R
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.components.CardNoteActivity
import com.example.habitance.ui.components.CardNoteUser
import com.example.habitance.ui.screens.activity_list.fetchActivities
import com.example.habitance.ui.screens.add_activity.Activity
import com.example.habitance.ui.screens.add_activity.CategoryActivity
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextLight
import com.example.habitance.ui.theme.fontFamily
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ListNote(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .padding(0.dp, 15.dp)
    ) {
        var isOpenedUser by remember { mutableStateOf(false) }
        var isOpenedActivity by remember { mutableStateOf(false) }

        val activities = remember { mutableStateOf<List<Activity>>(emptyList()) }
        val filteredActivities = remember { mutableStateOf<List<Activity>>(emptyList()) }
        val searchQuery = remember { mutableStateOf("") }
        val errorMessage = remember { mutableStateOf<String?>(null) }
        val selectedCategory = remember { mutableStateOf(CategoryActivity.Baik) }

        LaunchedEffect(Unit) {
            fetchActivities(
                onResult = { fetchedActivities ->
                    activities.value = fetchedActivities.filter {
                        it.end.seconds >= Timestamp.now().seconds
                    }
                    filteredActivities.value = fetchedActivities
                    filteredActivities.value = activities.value.filter {
                        it.category == selectedCategory.value
                    }
                },
                onError = { error ->
                    errorMessage.value = error.message
                }
            )
        }

        LaunchedEffect(selectedCategory.value) {
            filteredActivities.value = activities.value.filter {
                it.category == selectedCategory.value
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back icon",
                tint = TextDark,
                modifier = Modifier
                    .clickable {
                        navController.navigate("home")
                    }
                    .align(Alignment.CenterStart)
                    .padding(start = 32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(61.dp)
                    .height(51.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(Modifier.size(15.dp))
        Card(
            colors = CardDefaults.cardColors(BackGround2),
            border = BorderStroke(1.dp, Border),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 0.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(28.dp)
            ) {
                if ((isOpenedUser && isOpenedActivity) || (!isOpenedUser && !isOpenedActivity)){                    Text(
                        text = "ACTIVITY NOTE",
                        fontSize = 22.sp,
                        color = TextDark,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(600)
                    )
                    Spacer(Modifier.size(25.dp))
                }
                if (!isOpenedActivity) {
                    CardNoteUser(
                        isOpened = isOpenedUser,
                        onToggle = { isOpenedUser = !isOpenedUser })
                }
                if (!isOpenedActivity){
                    Spacer(modifier = Modifier.size(23.dp))
                    Text(
                        text = "Activity Note",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(600),
                            color = TextDark,
                        )
                    )
                    Spacer(Modifier.size(20.dp))
                    BasicTextField(
                        value = searchQuery.value,
                        onValueChange = { query ->
                            searchQuery.value = query
                            filteredActivities.value = activities.value.filter {
                                it.name.contains(query, ignoreCase = true)
                            }
                        },
                        modifier = Modifier
                            .background(TextLight, shape = RoundedCornerShape(size = 20.dp))
                            .border(width = 1.dp, color = TextDark, shape = RoundedCornerShape(size = 20.dp))
                            .height(30.dp)
                            .fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon",
                                    tint = TextDark,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp)
                                        .size(16.dp)
                                )
                                Box(
                                    Modifier.weight(1f),
                                    contentAlignment = Alignment.CenterStart
                                ){
                                    if(searchQuery.value.isEmpty()){
                                        Text(
                                            text = "Search",
                                            fontSize = 11.sp,
                                            color = TextDark.copy(alpha = 0.5f),
                                            fontFamily = fontFamily
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )

                    Spacer(Modifier.size(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        var isClickBaik by remember { mutableStateOf(true) }
                        var isClickBuruk by remember { mutableStateOf(false) }
                        Button(
                            onClick = {
                                isClickBaik = true
                                isClickBuruk = false
                                selectedCategory.value = CategoryActivity.Baik
                            },
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp)),
                            colors = if(isClickBaik) ButtonDefaults.buttonColors(TextDark) else ButtonDefaults.buttonColors(TextLight),
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            Text(
                                text = CategoryActivity.Baik.name,
                                color = if(isClickBaik) TextLight else TextDark,
                                fontFamily = fontFamily
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Button(
                            onClick = {
                                isClickBaik = false
                                isClickBuruk = true
                                selectedCategory.value = CategoryActivity.Buruk
                            },
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp)),
                            colors = if(isClickBuruk) ButtonDefaults.buttonColors(TextDark) else ButtonDefaults.buttonColors(TextLight),
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            Text(
                                text = CategoryActivity.Buruk.name,
                                color = if(isClickBuruk) TextLight else TextDark,
                                fontFamily = fontFamily
                            )
                        }
                    }
                    Spacer(Modifier.size(20.dp))
                }

                if (filteredActivities.value.isEmpty()) {
                    // Tampilan jika tidak ada aktivitas
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No activities found.",
                            color = TextDark,
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    LazyColumn() {
                        items(filteredActivities.value) { activity ->
                            Log.d("ActivityList", "Activity: ${activity.name}")
                            CardNoteActivity(
                                activity = activity,
                                isOpened = isOpenedActivity,
                                onToggle = { isOpenedActivity = !isOpenedActivity }
                            )
                            Spacer(Modifier.size(15.dp))
                        }
                    }
                }


//                LazyColumn {
//                    items(filteredActivities.value) { activity ->
////                        Log.d("ActivityList", "Activity: $activity")
//                        NoteItem("Membaca", "Belajar membaca 2 hari sekali")
//                    }
//                }
            }
        }
    }
}



//fun fetchActivities(
//    onResult: (List<Activity>) -> Unit,
//    onError: (Exception) -> Unit
//) {
//    val firestore = FirebaseFirestore.getInstance()
//    val currentUser = FirebaseAuth.getInstance().currentUser
//
//    if (currentUser != null) {
//        firestore.collection("users")
//            .document(currentUser.uid)
//            .collection("activities")
//            .get()
//            .addOnSuccessListener { querySnapshot ->
//                val activities = querySnapshot.documents.mapNotNull { document ->
//                    try {
//                        Activity(
//                            name = document.getString("name") ?: "",
//                            unit = document.getString("unit") ?: "",
//                            target = document.getString("target") ?: "",
//                            category = document.getString("category") ?: "",
//                            start = document.getString("start"),
//                            end = document.getString("end")
//                        )
//                    } catch (e: Exception) {
//                        null
//                    }
//                }
//                onResult(activities) // Return the list of activities
//            }
//            .addOnFailureListener { exception ->
//                onError(exception) // Return the error
//            }
//    } else {
//        onError(Exception("User is not logged in"))
//    }
//}

@Preview
@Composable
fun ListNotePreview() {
    ListNote(navController = rememberNavController())
}