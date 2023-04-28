package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavBarReturnButton(
    listElements: List<@Composable ()->Unit>,
    navFunc:()->Unit
) {
      Row(
          Modifier
              .background(color = MaterialTheme.colorScheme.primaryContainer)
              .fillMaxWidth()
              .height(60.dp),
          verticalAlignment = Alignment.CenterVertically
      ) {

          ReturnBackButton(navFunc)

          for (element in listElements) { element() }

      }
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