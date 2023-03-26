package com.example.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karaokeapp.MainActivityViewModel


@Composable
fun LoginFormBox(
    mainViewModel: MainActivityViewModel,
    hideFormBox: () -> Unit
){
    var id by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            Modifier
                .background(color = Color.LightGray)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                value = id,
                onValueChange = { newText -> id = newText },
                label = { Text("Adresse email") },
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email icon"
                )},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )

            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                value = password,
                onValueChange = { newText -> password = newText },
                label = { Text("Mot de passe") },
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Password icon"
                )},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        mainViewModel.authenticate(id, password)
                        hideFormBox()
                    }
                )
            )


            Button(
                modifier = Modifier.padding(vertical = 10.dp),
                onClick = {
                    mainViewModel.authenticate(id, password)
                    hideFormBox()
                }
            ) {
                Text(
                    text = "Connexion",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}
