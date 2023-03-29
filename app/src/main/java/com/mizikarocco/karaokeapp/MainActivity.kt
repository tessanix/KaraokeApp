package com.mizikarocco.karaokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mizikarocco.karaokeapp.navigation.SetupNavGraph
import com.mizikarocco.karaokeapp.ui.theme.KaraokeAppTheme
import com.mizikarocco.karaokeapp.ui.theme.rememberWindowSizeClass


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

