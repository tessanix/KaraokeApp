package com.example.karaokeapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karaokeapp.R
import com.example.karaokeapp.components.navBarReturnButton
import com.example.karaokeapp.ui.theme.AppTheme
import com.example.karaokeapp.ui.theme.Orientation

@Composable
fun AboutScreen(onGoHome : () -> Unit) {

    if (AppTheme.orientation == Orientation.Portrait){
        AboutScreenPortrait(onGoHome)
    }else{
        AboutScreenLandScape(onGoHome)
    }
}

@Composable
fun AboutScreenPortrait(onGoHome : () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {

            navBarReturnButton(isPortrait = true, onGoHome)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp)
                        .border(1.dp, Color.Black),
                    painter = painterResource(R.drawable.rocco_1),
                    contentDescription = null,

                    )
                Text(
                    text = "Animateur Karaoké",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )

            }
        }

        Text(
            text = "Frédy GOMBAUD-SAINTONGE dit Rocco",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Row{
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(horizontal = 10.dp),
                imageVector = Icons.Default.Phone, contentDescription = ""
            )
            Text(
                text = "0690 47 18 18",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "Anniversaires, Soirées, Manifestations, etc.",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun AboutScreenLandScape(onGoHome : () -> Unit) {

    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        navBarReturnButton(isPortrait = false, onGoHome)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxHeight().padding(vertical = 5.dp),
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
                    .border(1.dp, Color.Black),
                painter = painterResource(R.drawable.rocco_1),
                contentDescription = null,

                )
            Text(
                text = "Animateur Karaoké",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            ){

            Text(
                text = "Frédy GOMBAUD-SAINTONGE\ndit Rocco",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
            )
            Row {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = 10.dp),
                    imageVector = Icons.Default.Phone, contentDescription = ""
                )
                Text(
                    text = "0690 47 18 18",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = "Anniversaires, Soirées, Manifestations, etc.",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }


    }
}