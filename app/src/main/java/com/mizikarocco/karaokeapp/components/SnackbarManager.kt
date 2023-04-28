package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mizikarocco.karaokeapp.ui.theme.GreenSuccess
import com.mizikarocco.karaokeapp.ui.theme.RedError
import com.mizikarocco.karaokeapp.ui.theme.spacing

@Composable
fun SnackbarManager(data: SnackbarData) {
    when {
        data.visuals.message.contains("Erreur") -> {
            CustomSnackbar( Icons.Default.Error, RedError, data.visuals.message )
        }
        data.visuals.message.contains("votre nom") -> {
            CustomSnackbar( Icons.Default.Error, RedError, data.visuals.message )
        }
        data.visuals.message.contains("connexion") -> {
            CustomSnackbar( Icons.Default.SignalWifiConnectedNoInternet4, RedError, data.visuals.message )
        }
        data.visuals.message.contains("envoyée!") -> {
            CustomSnackbar( Icons.Default.Verified, GreenSuccess, data.visuals.message )
        }
    }
}



@Composable
fun CustomSnackbar(icon: ImageVector, backgroundColor: Color, message: String ) {
    Snackbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        containerColor = backgroundColor,
        contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(backgroundColor),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){

            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = icon,
                contentDescription = null
            )

            Spacer( Modifier.width(MaterialTheme.spacing.medium) )

            Text(
                text = message,
                //color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}



