package com.example.habitance.ui.screens.activity_list

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.habitance.ui.screens.add_activity.Activity
import com.google.firebase.Timestamp


@Composable
fun ActivityScreen(navController: NavController) {

    val activities = remember { mutableStateOf<List<Activity>>(emptyList()) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    fetchActivities(
        onResult = { fetchedActivities ->
            activities.value = fetchedActivities.filter {
                it.end.seconds >= Timestamp.now().seconds
            }
            isLoading.value = false
        },
        onError = { error ->
            errorMessage.value = error.message
        }
    )

    if (isLoading.value) {
        CircularProgressIndicator()
    } else {
        if (activities.value.isEmpty()) {
            ActivityListEmpty("Add Activity", navController)
        } else {
            ListActivity(navController)
        }
    }
}

