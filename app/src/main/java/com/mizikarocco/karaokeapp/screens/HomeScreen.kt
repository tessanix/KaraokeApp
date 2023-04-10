package com.mizikarocco.karaokeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mizikarocco.karaokeapp.MainActivityViewModel
import com.mizikarocco.karaokeapp.R
import com.mizikarocco.karaokeapp.components.LoginFormBox
import com.mizikarocco.karaokeapp.ui.theme.AppTheme
import com.mizikarocco.karaokeapp.ui.theme.PinkDarkerMic


@Composable
fun HomeScreen(
    isAdmin: Boolean?,
    //mainViewModel: MainActivityViewModel,
    onGoMusics: () -> Unit,
    onGoAbout: () -> Unit
) {
    val mainViewModel : MainActivityViewModel = viewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)

    var isFormBoxDisplayed by remember { mutableStateOf(false) }

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

        AdminRow(isAdmin, { mainViewModel.signOut() }, isFormBoxDisplayed){
                newDisplay -> isFormBoxDisplayed = !newDisplay
        }
    }

    if(isFormBoxDisplayed){
        LoginFormBox(mainViewModel = mainViewModel){
            isFormBoxDisplayed = false
        }
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


@Composable
fun AdminRow(
    isAdmin:Boolean?,
    signOut: () -> Unit,
    displayForm: Boolean,
    displayLoginForm: (Boolean) -> Unit,
){
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimens.small)

    ) {
        if (isAdmin == true) {
            Text(
                modifier = Modifier.padding(AppTheme.dimens.medium),
                text = "Admin Mode activated",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )

            Text(
                modifier = Modifier.clickable{ signOut() },
                text = "Disconnect",
                style = MaterialTheme.typography.h6
            )

        } else {
            Text(
                modifier = Modifier.clickable{ displayLoginForm(displayForm) },
                text = "Login as admin",
                style = MaterialTheme.typography.h6
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview(){
//    HomeScreen(isAdmin = false , mainViewModel = viewModel(), onGoMusics = {}, onGoAbout = {})
//}
