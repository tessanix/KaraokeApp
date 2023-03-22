package com.example.karaokeapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karaokeapp.components.ExpandableCard
import com.example.karaokeapp.components.navBarReturnButton
import com.example.karaokeapp.data.Song
import com.example.karaokeapp.ui.theme.AppTheme
import com.example.karaokeapp.ui.theme.MainActivityViewModel
import com.example.karaokeapp.ui.theme.Orientation


@Composable
fun MusicsScreen(
    onGoHome : () -> Unit
) {
    val mainViewModel =  viewModel<MainActivityViewModel>()

    val songs by produceState<Map<String, List<Song>>>(emptyMap(), mainViewModel) { value = mainViewModel.getSongs() }
    val isAdmin = mainViewModel.isAdmin.value

    Log.d("Admin value in MusicScreen", mainViewModel.isAdmin.value.toString())

    if (AppTheme.orientation == Orientation.Portrait){
        if (isAdmin != null) {
            MusicsScreenPortrait(isAdmin, songs = songs, onGoHome)
        }

    }else{
        if (isAdmin != null) {
            MusicsScreenLandscape(isAdmin, songs = songs, onGoHome)
        }
    }
}

/*
@Composable
fun AddButton( viewModel: MusicsViewModel){
    Button(onClick = {
        viewModel.viewModelScope.launch{
            viewModel.addSongs("Disney & Enfants","Géronimooo","Jotaro")
        }
    }
    ) {
        Text(
            text = "add new song",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}*/
@Composable
fun MusicsScreenPortrait(
    isAdmin: Boolean = false,
    songs : Map<String, List<Song>>,
    onGoHome : () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        navBarReturnButton(isPortrait = true, onGoHome, displayAddButton=isAdmin)
        //AddButton(viewModel)
        if(songs.isNotEmpty()){ ExpandableCard(songs, true) }
    }
}

@Composable
fun MusicsScreenLandscape(
    isAdmin: Boolean = false,
    songs : Map<String, List<Song>>,
    onGoHome : () -> Unit
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        navBarReturnButton(isPortrait = false, onGoHome, displayAddButton=isAdmin)
        //AddButton(viewModel)
        if(songs.isNotEmpty()){ ExpandableCard(songs, false) }
    }
}


@Composable
@Preview(showBackground = true)
fun MusicsScreenPreview(){
    MusicsScreen{ }
}
