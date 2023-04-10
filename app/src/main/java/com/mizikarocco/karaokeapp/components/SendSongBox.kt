package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizikarocco.karaokeapp.MusicsViewModel
import com.mizikarocco.karaokeapp.data.Song


@Composable
fun SendSongBox(
    clientName : String,
    song : Song,
    musicsViewModel: MusicsViewModel,
    hideFormBox: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .background(color = Color.LightGray)
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = hideFormBox) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = ""
                    )
                }
            }
            Text(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                text = "Vous allez envoyer les informations suivantes à Rocco:"
            )
            Text(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                text = "Nom: $clientName \n" +
                        "Morceau: ${song.title} \n" +
                        "         ${song.author}"
            )

            Row {
                Button(
                    modifier = Modifier.padding(vertical = 10.dp),
                    onClick = { musicsViewModel.addClientRequest(clientName, song)}//mainViewModel.sendRequestToServer(song) }
                ) {
                    Text(
                        text = "Ok",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Button(
                    modifier = Modifier.padding(vertical = 10.dp),
                    onClick = { hideFormBox() }
                ) {
                    Text(
                        text = "Annuler",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
