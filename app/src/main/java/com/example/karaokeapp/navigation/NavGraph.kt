package com.example.karaokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.karaokeapp.screens.AboutScreen
import com.example.karaokeapp.screens.HomeScreen
import com.example.karaokeapp.screens.MusicsScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController,
){

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(route = Screen.Home.route){

            HomeScreen (
                { navController.navigate(route = Screen.Musics.route) },
                { navController.navigate(route = Screen.About.route) }
            )
        }

        composable(route = Screen.Musics.route){
            MusicsScreen {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
        }

        composable(route = Screen.About.route){
            AboutScreen{
                navController.navigate(Screen.Home.route){
                    popUpTo(Screen.Home.route){ inclusive = true }
                }
            }
        }
    }
}
