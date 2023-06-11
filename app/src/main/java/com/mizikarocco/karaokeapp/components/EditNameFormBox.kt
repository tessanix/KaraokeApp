package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mizikarocco.karaokeapp.MainViewModel
import com.mizikarocco.karaokeapp.ui.theme.spacing


@Composable
fun EditNameFormBox(
    hideFormBox: () -> Unit
) {
    val mainViewModel: MainViewModel =
        viewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)

    var clientName by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false)}

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            Modifier
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .border(2.dp, shape = MaterialTheme.shapes.small, color = MaterialTheme.colorScheme.onSecondaryContainer )
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
                text = buildAnnotatedString {
                    if(mainViewModel.clientName.value.isNullOrBlank()) append("Vous n'avez pas encore de nom")
                    else {
                        append("Votre nom actuel est: ")
                        withStyle(
                            style = SpanStyle(
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                        ){ append(mainViewModel.clientName.value) }
                    }
                },
                color = MaterialTheme.colorScheme.onTertiaryContainer,
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
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ),
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
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}