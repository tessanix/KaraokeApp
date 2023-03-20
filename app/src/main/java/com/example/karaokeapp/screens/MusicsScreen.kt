package com.example.karaokeapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.karaokeapp.ui.theme.WitheMic
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karaokeapp.components.ExpandableCard
import com.example.karaokeapp.components.navBarReturnButton
import com.example.karaokeapp.data.Song
import com.example.karaokeapp.ui.theme.AppTheme
import com.example.karaokeapp.ui.theme.Orientation


@Composable
fun MusicsScreen(
    viewModel: MusicsViewModel = viewModel(),
    onGoHome : () -> Unit
) {
    val songs by produceState<Map<String, List<Song>>>(emptyMap(), viewModel) { value = viewModel.getSongs() }

    if (AppTheme.orientation == Orientation.Portrait){
        MusicsScreenPortrait(songs = songs, onGoHome)
    }else{
        MusicsScreenLandscape(songs = songs, onGoHome)
    }
}

@Composable
fun MusicsScreenPortrait(
    songs : Map<String, List<Song>>,
    onGoHome : () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        navBarReturnButton(isPortrait = true, onGoHome)

        if(songs.isNotEmpty()){ ExpandableCard(songs, true) }
    }
}

@Composable
fun MusicsScreenLandscape(
    songs : Map<String, List<Song>>,
    onGoHome : () -> Unit
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        navBarReturnButton(isPortrait = false, onGoHome)

        if(songs.isNotEmpty()){ ExpandableCard(songs, false) }
    }
}


@Composable
@Preview(showBackground = true)
fun MusicsScreenPreview(){
    MusicsScreen(viewModel()){ }
}
