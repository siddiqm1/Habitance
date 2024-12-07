package com.example.habitance.ui.screens.activitylist

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.habitance.ui.components.ListEmpty
import com.example.habitance.ui.screens.addactivity.Activity


@Composable
fun ActivityScreen(navController: NavController) {

    val activities = remember { mutableStateOf<List<Activity>>(emptyList()) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    fetchActivities(
        onResult = { fetchedActivities ->
            activities.value = fetchedActivities
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
            ListEmpty("Add Activity", navController)
        } else {
            ListActivity(navController)
        }
    }
}

