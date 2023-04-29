package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    screenName: String,
    listElements: List<@Composable ()->Unit>,
    navFunc : () -> Unit
){
    TopAppBar(
        scrollBehavior = null,
        title = { Text(text = screenName) },
        navigationIcon = {
            Row {
                ReturnBackButton(navFunc)
                for (element in listElements) { element() }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp)
        )
    )
}

@Composable
fun ReturnBackButton(
    navFunc : () -> Unit
){
    IconButton(onClick = navFunc) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    }
}

