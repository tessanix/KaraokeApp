package com.example.karaokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.karaokeapp.screens.AboutScreen
import com.example.karaokeapp.screens.HomeScreen
import com.example.karaokeapp.screens.MusicsScreen
import com.example.karaokeapp.ui.theme.MainActivityViewModel


@Composable
fun SetupNavGraph(
    navController: NavHostController,
){
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
         "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(route = Screen.Home.route){

            HomeScreen (
                viewModel(viewModelStoreOwner = viewModelStoreOwner),
                { navController.navigate(route = Screen.Musics.route) },
                { navController.navigate(route = Screen.About.route) }
            )
        }

        composable(route = Screen.Musics.route){

            CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
                MusicsScreen {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
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
