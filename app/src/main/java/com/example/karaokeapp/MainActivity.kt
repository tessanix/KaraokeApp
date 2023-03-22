package com.example.karaokeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.karaokeapp.navigation.SetupNavGraph
import com.example.karaokeapp.ui.theme.KaraokeAppTheme
import com.example.karaokeapp.ui.theme.rememberWindowSizeClass
import com.google.firebase.auth.FirebaseAuth


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

