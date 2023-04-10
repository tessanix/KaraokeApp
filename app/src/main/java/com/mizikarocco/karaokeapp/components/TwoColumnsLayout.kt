package com.mizikarocco.karaokeapp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

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