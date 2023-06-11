package com.mizikarocco.karaokeapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mizikarocco.karaokeapp.R
import com.mizikarocco.karaokeapp.components.CustomTopAppBar
import com.mizikarocco.karaokeapp.ui.theme.spacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onGoHome : () -> Unit) {

    val gradient = Brush.linearGradient(
        .14f to Color(0xffad720f),
        .47f to Color(0xff39b7ed),
        .78f to Color(0xff5905e7),
        start = Offset.Zero,
        end = Offset.Infinite
    )
    Scaffold(
        topBar = {
            CustomTopAppBar(screenName = "À propos ...", listElements = emptyList(),  navFunc = onGoHome)
        },
        content = { innerPading ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
                    .padding(innerPading),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .clip(RoundedCornerShape(50.dp))
                        .border(5.dp, Color.White, RoundedCornerShape(50.dp))
                        .fillMaxWidth(0.8f)
                        .weight(.4f),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(R.drawable.rocco_1),
                    contentDescription = null,
                )

                Column(
                    modifier = Modifier.weight(.6f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(R.drawable.name_and_rocco),
                        contentDescription = null,
                    )

                    Text(
                        text = "Animateur Karaoké",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Anniversaires, Soirées, Manifestations, etc.",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "✆ 0690 47 18 18",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(onGoHome = {})
}
