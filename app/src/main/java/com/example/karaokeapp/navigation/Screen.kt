package com.example.karaokeapp.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")

    object Musics: Screen(route = "musics_screen") ///{id}"){
//        fun giveId(id: String): String{
//            return "musics_screen/${id}"
//        }
//    }
    object About: Screen(route = "about_screen")

}