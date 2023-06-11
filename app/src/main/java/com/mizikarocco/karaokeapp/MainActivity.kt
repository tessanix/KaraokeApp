package com.mizikarocco.karaokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.mizikarocco.karaokeapp.navigation.SetupNavGraph
import com.mizikarocco.karaokeapp.ui.theme.KaraokeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaraokeAppTheme {
                Surface(color = MaterialTheme.colorScheme.background){
                    SetupNavGraph(navController = rememberNavController())
                }
            }
        }
    }
}

