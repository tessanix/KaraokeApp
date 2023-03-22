package com.example.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karaokeapp.ui.theme.MainActivityViewModel
import com.example.karaokeapp.ui.theme.WitheMic
import kotlinx.coroutines.launch

@Composable
fun navBarReturnButton(
    isPortrait:Boolean,
    navFunc:()->Unit,
    displayAddButton:Boolean=false
) {
    val mainViewModel =  viewModel<MainActivityViewModel>()

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
          if(displayAddButton){
              IconButton(onClick = {
                  mainViewModel.viewModelScope.launch{ mainViewModel.addSongs("Disney & Enfants","Géronimooo","Jotaro") }
              }) {
                  Icon(
                      modifier = Modifier.size(40.dp),
                      imageVector = Icons.Default.Add, contentDescription = "add song"
                  )
              }
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
          if(displayAddButton){
              IconButton(onClick = {
                  mainViewModel.viewModelScope.launch{ mainViewModel.addSongs("Disney & Enfants","Géronimooo","Jotaro") } }
              ) {
                  Icon(
                      modifier = Modifier.size(40.dp),
                      imageVector = Icons.Default.Add, contentDescription = "add song"
                  )
              }
          }
      }
    }
}