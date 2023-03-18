package com.example.karaokeapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.karaokeapp.ui.theme.WitheMic

@Composable
fun AboutScreen(onGoHome : () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            Modifier
                .background(color = WitheMic)
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton( onClick = onGoHome){
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.ArrowBack, contentDescription = "return to Home"
                )
            }
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)
        ){
            Image(
                modifier= Modifier
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
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Frédy GOMBAUD-SAINTONGE dit Rocco",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(80.dp))
        Row() {
            Icon(
                modifier = Modifier.size(50.dp).padding(horizontal = 10.dp),
                imageVector = Icons.Default.Phone, contentDescription = ""
            )
            Text(
                text = "0690 47 18 18",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(200.dp))

        Text(
            text = "Anniversaires, Soirées, Manifestations, etc.",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

    }
}