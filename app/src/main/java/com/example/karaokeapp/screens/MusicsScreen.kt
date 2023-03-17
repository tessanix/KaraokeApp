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
import com.example.karaokeapp.data.Song


@Composable
fun MusicsScreen(
    viewModel: MusicsViewModel = viewModel(),
    onGoHome : () -> Unit
) {

    val songs by produceState<Map<String, List<Song>>>(emptyMap(), viewModel) { value = viewModel.getSongs() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .background(color = WitheMic)
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton( onClick = onGoHome){
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.ArrowBack, contentDescription = "return to Home"
                )
            }
        }
        if (songs.isNotEmpty()) {
            ExpandableCard(songs)
        }

    }
}



@Composable
@Preview(showBackground = true)
fun MusicsScreenPreview(){
    MusicsScreen(viewModel()){ }
}
