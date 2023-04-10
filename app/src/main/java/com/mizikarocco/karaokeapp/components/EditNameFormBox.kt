package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizikarocco.karaokeapp.MainActivityViewModel


@Composable
fun EditNameFormBox(
    mainViewModel: MainActivityViewModel,
    hideFormBox: () -> Unit
) {
    var clientName by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false)}


    Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
    ){
        Column(
            Modifier
                .background(color= Color.LightGray)
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = hideFormBox) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = ""
                    )
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                value = clientName,
                onValueChange = { newText -> clientName = newText },
                label = { Text("Mon nom") },
                isError = isError
            )

            Button(
                modifier = Modifier.padding(vertical = 10.dp),
                onClick = {
                        if (clientName.isNotBlank()){
                            mainViewModel.saveToDataStore(clientName)
                            isError = false
                            hideFormBox()
                        }else{
                            isError = true
                        }
                }
            ) {
                Text(
                    text = "Sauvgarder mon nom",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}