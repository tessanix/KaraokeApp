package com.mizikarocco.karaokeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mizikarocco.karaokeapp.ui.theme.AppTheme
import com.mizikarocco.karaokeapp.ui.theme.GreenSuccess
import com.mizikarocco.karaokeapp.ui.theme.RedError

@Composable
fun SnackbarManager(data: SnackbarData) {
    when {
        data.message.contains("Erreur") -> {
            CustomSnackbar( Icons.Default.Error, RedError, data.message )
        }
        data.message.contains("votre nom") -> {
            CustomSnackbar( Icons.Default.Error, RedError, data.message )
        }
        data.message.contains("connexion") -> {
            CustomSnackbar( Icons.Default.SignalWifiConnectedNoInternet4, RedError, data.message )
        }
        data.message.contains("envoyée!") -> {
            CustomSnackbar( Icons.Default.Verified, GreenSuccess, data.message )
        }
    }
}



@Composable
fun CustomSnackbar(icon: ImageVector, backgroundColor: Color, message: String ) {
    Snackbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimens.medium),
        backgroundColor = backgroundColor,
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

            Spacer( Modifier.width(AppTheme.dimens.medium) )

            Text(
                text = message,
                //color = Color.Black,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

        }

    }

}



