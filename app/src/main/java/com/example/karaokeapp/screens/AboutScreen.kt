package com.example.karaokeapp.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen(onGoHome : () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        IconButton(onClick = onGoHome){
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.ArrowBack, contentDescription = "return to Home"
            )
        }

        //ExpandableCard()

        Text(
            text = "A propos de Rocco",
            fontSize = 50.sp,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onGoHome,
            modifier = Modifier.height(50.dp)
        ) {
            Text(text = "Return Home")
        }
    }
}