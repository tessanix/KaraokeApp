package com.example.karaokeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karaokeapp.R

import com.example.karaokeapp.ui.theme.PinkDarkerMic


@Composable
fun HomeScreen( onGoMusics : () -> Unit, onGoAbout : () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        Text(
            text = "Rocco Karaoké",
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
        )


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(350.dp)
                .border(width = 10.dp, shape = CircleShape, color = PinkDarkerMic)

        ) {
            Image(
                painter = painterResource(R.drawable.icon_karaoke),
                contentDescription = null,
                modifier = Modifier.size(220.dp)
            )
        }
        Button(
            onClick = onGoMusics,//navController.navigate(route = Screen.Musics.route) },
            modifier = Modifier
                .height(70.dp)
                .width(180.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PinkDarkerMic,  contentColor = Color.White)
        ) {
            Text(
                text = "Musiques",
                fontSize = 20.sp,
            )
        }
        Button(
            onClick =  onGoAbout,//{ navController.navigate(route = Screen.About.route) },
            modifier = Modifier
                .height(70.dp)
                .width(180.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PinkDarkerMic, contentColor = Color.White)
        ) {
            Text(
                text = "À propos de Rocco",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    HomeScreen( {}, {})
}