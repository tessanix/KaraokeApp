package com.mizikarocco.karaokeapp.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.ui.theme.Shapes

@Composable
fun ExpandableCard(
    songListMappedByCategories: Map<String, List<Song>>,
    isSendSongBoxDisplayed: Boolean,
    registerRequestedSong: (Song) -> Unit,
    displaySendSongBox : (Boolean) -> Unit
) {

    val expandedStates = remember {
        mutableStateMapOf(
            *songListMappedByCategories.keys.map { it to false }.toTypedArray()
        )
    }


    LazyColumn(
        Modifier.fillMaxWidth()
    ) {

        for((category, songList) in songListMappedByCategories){

            item {
                expandedStates[category]?.let { boolState ->
                    CategoryHeader(
                        categoryTitle = category,
                        expandedState = boolState,
                        rotationState = animateFloatAsState(if(expandedStates[category] == true) 180f else 0f).value,
                        updateExpandedState = { bool -> expandedStates[category] = !bool }
                    )
                }
            }

            if (expandedStates[category] == true) {

                items(songList) { song ->
                    ColumnItem(
                        song = song,
                        isSendSongFormDisplayed = isSendSongBoxDisplayed,
                        changeRequestedSong = { registerRequestedSong(song) },
                        displaySendSongForm = { displaySendSongBox(isSendSongBoxDisplayed) }
                    )

                }

            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ColumnItem(
    song: Song,
    isSendSongFormDisplayed: Boolean,
    changeRequestedSong : (Song) -> Unit,
    displaySendSongForm: (Boolean) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.onPrimaryContainer, shape = MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.9f), //.padding(start = MaterialTheme.spacing.small )
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                text = song.title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 20.sp
            )
            Text(
                text = song.author,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.1f), //.aspectRatio(1F)
            onClick = {
                displaySendSongForm(isSendSongFormDisplayed)
                changeRequestedSong(song)
        }) {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                imageVector = Icons.Default.Send,
                contentDescription = null
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryHeader(
    categoryTitle : String,
    expandedState : Boolean,
    rotationState : Float,
    updateExpandedState: (Boolean) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ),
        colors = cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        shape = Shapes.medium,
        onClick = { updateExpandedState(expandedState) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                //.background(color = PinkDarkerMic)
        ) {
            Text(
                modifier = Modifier
                    .weight(6f)
                    .padding(horizontal = 10.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                text = categoryTitle,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .rotate(rotationState),
                onClick = { updateExpandedState(expandedState) }
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        }
    }
}

