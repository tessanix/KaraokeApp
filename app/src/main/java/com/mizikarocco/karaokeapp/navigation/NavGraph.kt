package com.mizikarocco.karaokeapp.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mizikarocco.karaokeapp.MainActivityViewModel
import com.mizikarocco.karaokeapp.data.Song
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

    val mainViewModel : MainActivityViewModel = viewModel(viewModelStoreOwner = viewModelStoreOwner)
    val adminState = mainViewModel.isAdmin.observeAsState()
    val admin = remember { adminState }

    val clientNameState = mainViewModel.clientName.observeAsState()
    val clientName by remember { clientNameState }



    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(route = Screen.Home.route){
            CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
                HomeScreen(
                    admin.value,
                    { navController.navigate(route = Screen.Musics.route) }
                ) { navController.navigate(route = Screen.About.route) }
            }
        }

        composable(route = Screen.Musics.route){
            //val songs by produceState<Map<String, List<Song>>>(emptyMap(), mainViewModel) { value = mainViewModel.getSongs() }

            CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
                    MusicsScreen(
                        admin.value,
                        clientName,
                       // songs
                    ) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
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
