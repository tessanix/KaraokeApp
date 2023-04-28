package com.mizikarocco.karaokeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mizikarocco.karaokeapp.R
import com.mizikarocco.karaokeapp.ui.theme.PinkDarkerMic
import com.mizikarocco.karaokeapp.ui.theme.spacing


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
            containerColor = PinkDarkerMic,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(MaterialTheme.spacing.medium),
        shape = CircleShape
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(onGoMusics = {}, onGoAbout = {})
}
