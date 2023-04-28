package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mizikarocco.karaokeapp.MainViewModel
import com.mizikarocco.karaokeapp.ui.theme.spacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNameFormBox(
    mainViewModel: MainViewModel,
    hideFormBox: () -> Unit
) {
    var clientName by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false)}

    val text = if(mainViewModel.clientName.value.isNullOrBlank()) "Vous n'avez pas encore de nom"
        else "Votre nom actuel est: ${mainViewModel.clientName.value}"

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
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                text = text,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

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