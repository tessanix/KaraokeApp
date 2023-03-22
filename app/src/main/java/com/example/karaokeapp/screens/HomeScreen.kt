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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karaokeapp.R
import com.example.karaokeapp.ui.theme.AppTheme
import com.example.karaokeapp.ui.theme.MainActivityViewModel
import com.example.karaokeapp.ui.theme.Orientation

import com.example.karaokeapp.ui.theme.PinkDarkerMic


@Composable
fun HomeScreen(
    viewModel: MainActivityViewModel,
    onGoMusics : () -> Unit,
    onGoAbout : () -> Unit
) {
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
        Button(
            onClick = { viewModel.authentificate() },
        ){
            Text(
                text = "login as admin",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { viewModel.signOut() },
        ){
            Text(
                text = "disconnect",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        if (AppTheme.orientation == Orientation.Portrait) {
            HomeScreenPortrait(onGoMusics, onGoAbout)
        } else {
            HomeScreenLandscape(onGoMusics, onGoAbout)
        }
    }
}
@Composable
fun HomeScreenPortrait(onGoMusics : () -> Unit, onGoAbout : () -> Unit){

    KaraokeRowImage()

    NavButton("Musiques", onGoMusics)

    NavButton("À propos de Rocco", onGoAbout)
}

@Composable
fun HomeScreenLandscape(onGoMusics : () -> Unit, onGoAbout : () -> Unit){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        KaraokeRowImage(310.dp, 190.dp)

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ){
            NavButton("Musiques", onGoMusics)

            NavButton("À propos de Rocco", onGoAbout)
        }

    }
}

@Composable
fun KaraokeRowImage(circleSize: Dp = 350.dp, imageSize:Dp = 220.dp){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .size(circleSize)
            .border(width = 10.dp, shape = CircleShape, color = PinkDarkerMic)
    ){
        Image(
            painter = painterResource(R.drawable.icon_karaoke),
            contentDescription = null,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
fun NavButton(text : String, navFunc: () -> Unit){
    Button(
        onClick =  navFunc,
        modifier = Modifier
            .height(70.dp)
            .width(180.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = PinkDarkerMic, contentColor = Color.White)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
