package com.mizikarocco.karaokeapp.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.ui.theme.PinkDarkerMic
import com.mizikarocco.karaokeapp.ui.theme.Shapes

@Composable
fun ExpandableCard(
    dataList: Map<String, List<Song>>,
    isSendSongBoxDisplayed: Boolean,
    changeRequestedSong: (Song) -> Unit,
    displaySendSongBox : (Boolean) -> Unit
) {

    val expandedStates = remember {
        mutableStateMapOf(
            *dataList.keys.map { it to false }.toTypedArray()
        )
    }


    LazyColumn(
        Modifier.fillMaxWidth()
    ) {

        for((category, songList) in dataList){

            item {
                expandedStates[category]?.let { boolState ->
                    CategoryHeader(
                        category,
                        boolState,
                        animateFloatAsState(if(expandedStates[category] == true) 180f else 0f).value
                    ){ bool -> expandedStates[category] = !bool }
                }
            }

            if (expandedStates[category] == true) {

                items(songList) { song ->
                    ColumnItem(song = song, isSendSongBoxDisplayed, { changeRequestedSong(song) }) {
                        displaySendSongBox(isSendSongBoxDisplayed)
                    }
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(.9f)
                //.background(Color.White)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                text = song.title,
                //modifier = Modifier.padding(horizontal = 10.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(
                text = song.author,
                //modifier = Modifier.padding(horizontal = 10.dp),
                color = Color.Black
            )
        }

        IconButton(
            modifier = Modifier.weight(.1f).border(2.dp, Color.Black, shape = RoundedCornerShape(10.dp)),
            onClick = {
                displaySendSongForm(isSendSongFormDisplayed)
                changeRequestedSong(song)
        }) {
            Icon(
                //modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Default.Send,
                contentDescription = null
            )
            //Text(text = "Envoyer le morceau à Rocco")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryHeader(
    categoryTitle: String,
    expandedState : Boolean,
    rotationState : Float,
    updateExpandedState: (Boolean) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        onClick = { updateExpandedState(expandedState) }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = PinkDarkerMic)
        ) {
            Text(
                modifier = Modifier
                    .weight(6f)
                    .padding(horizontal = 10.dp),
                color = Color.White,
                text = categoryTitle,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
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
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down arrow"
                )
            }
        }
    }
}

