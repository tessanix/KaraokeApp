package com.mizikarocco.karaokeapp.components


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
import com.mizikarocco.karaokeapp.MusicsViewModel
import kotlinx.coroutines.launch

@Composable
fun FormBox(
    musicsViewModel: MusicsViewModel,
    hideFormBox: () -> Unit
){
    var title by remember { mutableStateOf("") }

    var author by remember { mutableStateOf("") }

    var category by remember { mutableStateOf("") }

    var isError by remember { mutableStateOf(false)}

    val categoryList = listOf("Anglais","Français","Espagnol", "Disney & Enfants", "Zouk", "Chansons de Noel", "Bolero & Tango")

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
                label = {Text("Titre")},
                isError = isError
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                value = author,
                onValueChange = { newText -> author = newText },
                label = {Text("Auteur")},
                isError = isError
            )


            DropDownMenu(isError, categoryList){ category = it }

            Button(
                modifier = Modifier.padding(vertical = 10.dp),
                onClick = {
                    musicsViewModel.viewModelScope.launch {
                        Log.d("song added", "category $category, title $title, $author ")
                        if (categoryList.contains(category) && title != "" && author != ""){
                            musicsViewModel.addSongs(category, title, author)
                            isError = false
                            hideFormBox()
                        }else{
                            isError = true
                        }
                    }
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