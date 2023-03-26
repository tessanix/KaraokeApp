package com.example.karaokeapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize


@Composable
fun DropDownMenu(
    choices: List<String>,
    updateTextState: (String) -> Unit
){

    // Declaring a boolean value to store the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a string value to store the selected value in list
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(10.dp)) {

        // Create an Outlined Text Field with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = {
                newvalue -> mSelectedText = newvalue
                updateTextState(mSelectedText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text("Catégorie")},
            trailingIcon = {
                Icon(icon,"", Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()}),
        ) {
            choices.forEach { choice ->
                DropdownMenuItem(
                    onClick = {
                        mSelectedText = choice
                        updateTextState(choice)
                        mExpanded = false
                    }
                ) { Text(text = choice) }
            }
        }
    }
}

// For displaying preview in
// the Android Studio IDE emulator
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    DropDownMenu(emptyList())
//}