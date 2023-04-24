package com.mizikarocco.karaokeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mizikarocco.karaokeapp.R
import com.mizikarocco.karaokeapp.ui.theme.AppTheme
import com.mizikarocco.karaokeapp.ui.theme.PinkDarkerMic


@Composable
fun HomeScreen(
    onGoMusics: () -> Unit,
    onGoAbout: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_rocco_home),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.8f),
            contentScale = ContentScale.FillWidth
        )

        NavButton("Musiques", onGoMusics)

        NavButton("À propos de Rocco", onGoAbout)
    }
}


@Composable
fun NavButton(text : String, navFunc: () -> Unit){
    Button(
        onClick =  navFunc,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PinkDarkerMic,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(AppTheme.dimens.medium),
        shape = CircleShape
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(AppTheme.dimens.medium)
        )
    }
}
