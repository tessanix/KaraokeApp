package com.mizikarocco.karaokeapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mizikarocco.karaokeapp.R
import com.mizikarocco.karaokeapp.components.NavBarReturnButton
import com.mizikarocco.karaokeapp.ui.theme.AppTheme


@Composable
fun AboutScreen(onGoHome : () -> Unit) {
    val gradient = Brush.linearGradient(
        .14f to Color(0xffad720f),
        .47f to Color(0xff39b7ed),
        .78f to Color(0xff5905e7),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Column(
        modifier = Modifier.fillMaxSize().background(gradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        NavBarReturnButton(listElements = emptyList(), navFunc = onGoHome)

        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .border(5.dp, Color.White, RoundedCornerShape(50.dp))
                .fillMaxWidth(0.7f),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.rocco_1),
            contentDescription = null,
        )

        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.name_and_rocco),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(AppTheme.dimens.medium),
            text = "Animateur Karaoké",
            color = Color.White,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(AppTheme.dimens.medium),
            text = "Anniversaires, Soirées, Manifestations, etc.",
            color = Color.White,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(AppTheme.dimens.medium),
            text = "✆ 0690 47 18 18",
            color = Color.White,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )

    }
}
