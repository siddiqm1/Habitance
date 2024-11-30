package com.example.habitance.navbar

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object ActivityScreen : Screen("activity")
    object NoteScreen : Screen("note")
    object FinisActivityScreen : Screen("finis")
    object AddActivityScreen : Screen("add_activity")
    object ProfileScreen : Screen("profile")

}