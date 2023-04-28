package com.mizikarocco.karaokeapp.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mizikarocco.karaokeapp.MainViewModel
import com.mizikarocco.karaokeapp.data.WebSocketResponse
import com.mizikarocco.karaokeapp.screens.AboutScreen
import com.mizikarocco.karaokeapp.screens.HomeScreen
import com.mizikarocco.karaokeapp.screens.MusicsScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
         "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    val mainViewModel : MainViewModel = hiltViewModel()// viewModel(viewModelStoreOwner = viewModelStoreOwner)

    val clientNameState = mainViewModel.clientName.observeAsState()
    val clientName by remember { clientNameState }

    var latestSocketState by remember { mutableStateOf<WebSocketResponse?>(null) }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(route = Screen.Home.route){
            HomeScreen(
                onGoMusics = { navController.navigate(route = Screen.Musics.route) },
                onGoAbout = { navController.navigate(route = Screen.About.route) }
            )
        }

        composable(route = Screen.Musics.route){
            CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
                MusicsScreen(
                    clientName = clientName,
                    latestSocketState = latestSocketState,
                    modifyLatestSocketState = { latestSocketState = it },
                    onGoHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(route = Screen.About.route){
            AboutScreen(
                onGoHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route){ inclusive = true }
                    }
                }
            )
        }
    }
}
