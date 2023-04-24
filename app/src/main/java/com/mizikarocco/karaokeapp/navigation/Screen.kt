package com.mizikarocco.karaokeapp.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")

    object Musics: Screen(route = "musics_screen")

    object About: Screen(route = "about_screen")
}