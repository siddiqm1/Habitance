package com.example.habitance.navbar

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object ActivityScreen : Screen("activity")
    object NoteScreen : Screen("note")
    object FinishedActivityScreen : Screen("finished")
    object FinishedActivityEmpty : Screen("listemptyfinished")
    object AddActivityScreen : Screen("addactivity")
    object ProfileScreen : Screen("profile")
    object ActivityListEmpty : Screen("listempty")
    object ActivityList : Screen("activitylist")
    object NotificationScreen : Screen("notification")
    object AddNotificationScreen : Screen("add_notification") // Baru

}