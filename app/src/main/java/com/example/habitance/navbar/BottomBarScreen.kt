package com.example.habitance.navbar

import com.example.habitance.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.home,
        icon_focused = R.drawable.home_focused
    )

    // for report
    object Activity: BottomBarScreen(
        route = "activity",
        title = "Activity",
        icon = R.drawable.activity,
        icon_focused = R.drawable.activity_focused
    )
    object Note : BottomBarScreen(
        route = "note",
        title = "Note",
        icon = R.drawable.note,
        icon_focused = R.drawable.note_focused
    )

    // for Finis
    object FinisActivity: BottomBarScreen(
        route = "finis",
        title = "FinisActivity",
        icon = R.drawable.finis,
        icon_focused = R.drawable.finish
    )

}
