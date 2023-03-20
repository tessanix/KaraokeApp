package com.example.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.karaokeapp.ui.theme.WitheMic

@Composable
fun navBarReturnButton(isPortrait:Boolean, navFunc:()->Unit) {

  if(isPortrait) {
      Row(
          Modifier
              .background(color = WitheMic)
              .fillMaxWidth()
              .height(60.dp),
          verticalAlignment = Alignment.CenterVertically
      ) {
          IconButton(onClick = navFunc) {
              Icon(
                  modifier = Modifier.size(40.dp),
                  imageVector = Icons.Default.ArrowBack, contentDescription = "return to Home"
              )
          }
      }
  } else{
      Column(
          Modifier
              .background(color = WitheMic)
              .fillMaxHeight()
              .width(60.dp),
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          IconButton(onClick = navFunc) {
              Icon(
                  modifier = Modifier.size(40.dp),
                  imageVector = Icons.Default.ArrowBack, contentDescription = "return to Home"
              )
          }
      }
  }
}