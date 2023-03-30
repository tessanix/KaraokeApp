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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.ui.theme.PinkDarkerMic
import com.mizikarocco.karaokeapp.ui.theme.Shapes


@Composable
fun ColumnItem(song: Song) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start

    ) {
        Text(
            text = song.title,
            modifier = Modifier.padding(horizontal = 10.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
        Text(text = song.author,
            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color.Black
        )
    }
}
@Composable
fun TwoColumnsLayout(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(
        modifier = modifier,
        content = content
    ){measurables, constraints ->

            val placeables = measurables.map { measurable ->
                measurable.measure(constraints.copy(maxWidth = constraints.maxWidth / 2))
            }

            val rowsHeight = mutableListOf<Int>()
            for(i in placeables.indices) {
                if(i % 2 != 0 ){
                    rowsHeight += maxOf(placeables[i-1].height, placeables[i].height)
                }else if (i == placeables.size-1){
                    rowsHeight += placeables[i].height
                }
            }
            layout(constraints.maxWidth, rowsHeight.sum()) {

                // Track the y co-ord we have placed children up to
                var xPosition = 0
                var yPosition = 0
                var index = 0
                var firstPlaceableHeight = 0

                // Place children in the parent layout
                placeables.forEach { placeable ->

                    // Position item on the screen
                    placeable.placeRelative(x = xPosition, y = yPosition)

                    if(index % 2 == 0){
                        firstPlaceableHeight = placeable.height
                        xPosition += placeable.width
                    }else{
                        xPosition = 0
                        yPosition += maxOf(firstPlaceableHeight, placeable.height)
                    }
                    index ++
                }
            }

    }
}

@OptIn(ExperimentalMaterialApi::class)
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
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
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

@Composable
fun ExpandableCard(dataList: Map<String, List<Song>>, isPortrait: Boolean) {

    val expandedStates = remember {
        mutableStateMapOf(
            *dataList.keys.map { it to false }.toTypedArray()
        )
    }

    LazyColumn(
        Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
    ) {

        for((category, songList) in dataList){

            item {
                expandedStates[category]?.let { boolState ->
                    CategoryHeader(category, boolState, animateFloatAsState(if(expandedStates[category] == true) 180f else 0f).value
                    ){ bool-> expandedStates[category] = !bool }
                }
            }

            if (expandedStates[category] == true) {
                if(isPortrait) {
                    items(songList) { song -> ColumnItem(song = song) }
                }else{
                    item{ TwoColumnsLayout { for (song in songList) { ColumnItem(song = song) } } }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExpandableCardPreview() {
    ExpandableCard(dataList = mutableMapOf(), true)
}




