package com.example.karaokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.karaokeapp.navigation.Screen
import com.example.karaokeapp.navigation.SetupNavGraph
import com.example.karaokeapp.screens.MusicsScreen
import com.example.karaokeapp.ui.theme.KaraokeAppTheme
import com.example.karaokeapp.ui.theme.rememberWindowSizeClass


class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val window = rememberWindowSizeClass()

            KaraokeAppTheme(window) {
                navController = rememberNavController()

                SetupNavGraph(navController = navController)
            }
        }
    }
}

