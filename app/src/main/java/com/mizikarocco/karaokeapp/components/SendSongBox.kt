package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mizikarocco.karaokeapp.MainViewModel
import com.mizikarocco.karaokeapp.data.ClientRequest
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.ui.theme.spacing


@Composable
fun SendSongBox(
    clientName : String,
    song : Song,
    mainViewModel: MainViewModel,
    showSnackBarError: () -> Unit,
    hideFormBox: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .border(2.dp, shape = MaterialTheme.shapes.small, color = MaterialTheme.colorScheme.onSecondaryContainer )
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = hideFormBox) {
                    Icon(imageVector = Icons.Default.Close,null)
                }
            }
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                text = "Vous allez envoyer la demande suivante à Rocco:"
            )
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                text = "Nom: $clientName \n" +
                        "Morceau: ${song.title} \n" +
                        "         ${song.author}"
            )

            Row {
                Button(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    onClick = {
                        if(clientName.isNotBlank()) {
                            mainViewModel.sendClientRequest(ClientRequest(clientName, song.title, song.author))
                            hideFormBox()
                        }
                        else showSnackBarError()
                    }
                ) {
                    Text(
                        text = "Ok",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }

                Button(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    onClick = { hideFormBox() }
                ) {
                    Text(
                        text = "Annuler",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
