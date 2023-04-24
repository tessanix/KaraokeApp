package com.mizikarocco.karaokeapp.screens


import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mizikarocco.karaokeapp.MainViewModel
import com.mizikarocco.karaokeapp.components.*
import kotlinx.coroutines.launch


@Composable
fun MusicsScreen (
    clientName: String?,
    onGoHome : () -> Unit
) {
    val mainViewModel : MainViewModel = viewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)

    val searchText by mainViewModel.searchText.collectAsState()
    val songs by mainViewModel.songs.collectAsState()
    val isConnecting by mainViewModel.isConnecting.collectAsState()
    val showConnectionError by mainViewModel.showConnectionError.collectAsState()

    var isEditNameDisplayed by remember { mutableStateOf(false) }
    var isSendSongBoxDisplayed by remember { mutableStateOf(false) }
    var isToastDisplayed by remember { mutableStateOf(false) }
    val animatableAlpha = remember { Animatable(initialValue = 1F) }
    var toastTextMessage by remember{ mutableStateOf("")}
    var toastIcon by remember{ mutableStateOf(Icons.Default.Verified) }

    val elementsNavBar = listOf<@Composable () -> Unit> {
        AddClientName(isEditNameDisplayed) { newDisplay ->
            isEditNameDisplayed = !newDisplay
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        NavBarReturnButton( listElements = elementsNavBar, navFunc = onGoHome)
        
        TextField(
            value = searchText,
            onValueChange = mainViewModel::onSearchTextChange,
            label = { Text("Rechercher") },
            modifier = Modifier.fillMaxWidth()
        )

        if (songs?.isNotEmpty() == true) {
            ExpandableCard(
                songs!!,
                isSendSongBoxDisplayed,
                { song -> mainViewModel.songRequested.value = song }
            ){ newDisplay -> isSendSongBoxDisplayed = !newDisplay }
        }
    }


    if(isSendSongBoxDisplayed && mainViewModel.songRequested.value != null && clientName != null){
        SendSongBox(
            clientName = clientName,
            song = mainViewModel.songRequested.value!!,
            mainViewModel = mainViewModel,
            { text, icon ->
                toastTextMessage = text
                toastIcon = icon

                mainViewModel.viewModelScope.launch{
                   isToastDisplayed = false
                   animatableAlpha.snapTo(1F)
                   isToastDisplayed = true
               }

            }
        ) { isSendSongBoxDisplayed = false }
    }

    if(isEditNameDisplayed){
        EditNameFormBox(mainViewModel = mainViewModel){
            isEditNameDisplayed = false
        }
    }

    if(isConnecting) LoadingAnimation()

    if(showConnectionError) CustomToast("Erreur de connexion!", Icons.Default.SignalWifiConnectedNoInternet4, null){}

    if(isToastDisplayed) CustomToast(toastTextMessage, toastIcon, animatableAlpha) { isToastDisplayed = false }
}


@Composable
fun AddClientName(
    displayEditNameForm: Boolean,
    addFunc: (Boolean) -> Unit
){
    IconButton(onClick = { addFunc(displayEditNameForm) } ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.AccountBox,
            contentDescription = "edit client name"
        )
    }
}

