package com.example.karaokeapp.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.karaokeapp.MainActivityViewModel
import kotlinx.coroutines.launch

@Composable
fun FormBox(
    mainViewModel: MainActivityViewModel,
    hideFormBox: () -> Unit
){
    var title by remember { mutableStateOf("") }

    var author by remember { mutableStateOf("") }

    var category by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            Modifier
                .background(color= androidx.compose.ui.graphics.Color.LightGray)
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                IconButton(onClick = hideFormBox) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "" )
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                value = title,
                onValueChange = { newText -> title = newText },
                label = {Text("Titre")}
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                value = author,
                onValueChange = { newText -> author = newText },
                label = {Text("Auteur")}
            )


            DropDownMenu(
                listOf("Anglais","Français","Espagnol", "Disney & Enfants", "Zouk", "Chansons de Noel", "Bolero & Tango")
            ){ category = it }

            Button(
                modifier = Modifier.padding(vertical = 10.dp),
                onClick = {
                mainViewModel.viewModelScope.launch {
                    Log.d("song added", "category $category, title $title, $author ")
                    mainViewModel.addSongs(
                            category,
                            title,
                            author
                    )}
                hideFormBox()
                }
            ) {
                Text(
                    text = "Ajoute un nouveau son",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}