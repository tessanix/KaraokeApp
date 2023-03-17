package com.example.karaokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.karaokeapp.navigation.SetupNavGraph
import com.example.karaokeapp.ui.theme.KaraokeAppTheme


class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaraokeAppTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

