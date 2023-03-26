package com.example.karaokeapp.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.karaokeapp.data.Song

class MusicsViewModel: ViewModel() {

    var state by mutableStateOf(Song("", ""))

}


