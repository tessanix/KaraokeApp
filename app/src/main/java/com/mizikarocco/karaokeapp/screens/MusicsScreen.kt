package com.mizikarocco.karaokeapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mizikarocco.karaokeapp.MainViewModel
import com.mizikarocco.karaokeapp.components.CustomTopAppBar
import com.mizikarocco.karaokeapp.components.EditNameFormBox
import com.mizikarocco.karaokeapp.components.ExpandableCard
import com.mizikarocco.karaokeapp.components.LoadingAnimation
import com.mizikarocco.karaokeapp.components.SendSongBox
import com.mizikarocco.karaokeapp.components.SnackbarManager
import com.mizikarocco.karaokeapp.data.WebSocketResponse
import com.mizikarocco.karaokeapp.ui.theme.spacing
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicsScreen (
    clientName: String?,
    latestSocketState: WebSocketResponse?,
    modifyLatestSocketState: (WebSocketResponse?)->Unit,
    onGoHome : () -> Unit
) {
    val mainViewModel: MainViewModel =
        viewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)

    val searchText by mainViewModel.searchText.collectAsState()
    val songs by mainViewModel.songs.collectAsState()
    val isConnecting by mainViewModel.isConnecting.collectAsState()
    val showConnectionError by mainViewModel.showConnectionError.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var isEditNameDisplayed by remember { mutableStateOf(false) }
    var isSendSongBoxDisplayed by remember { mutableStateOf(false) }


    val elementsNavBar = listOf<@Composable () -> Unit> {
        AddClientName(isEditNameDisplayed) { newDisplay ->
            isEditNameDisplayed = !newDisplay
        }
    }


    LaunchedEffect(true) {
        mainViewModel.socketState.collect { webSocketResponse ->
            //println("WEBSOKET IN COROUTINE: $webSocketResponse")
            if(webSocketResponse!=null && webSocketResponse!=latestSocketState) {

                if ((webSocketResponse.status == "Success") && (webSocketResponse.action == "addRequest"))
                    coroutineScope.launch{
                        snackbarHostState.showSnackbar("Requête envoyée!", duration = SnackbarDuration.Short)
                    }
                else  {
                    coroutineScope.launch{
                        snackbarHostState.showSnackbar("Erreur lors de l'envoie.", duration = SnackbarDuration.Short)
                    }
                }
                modifyLatestSocketState(webSocketResponse)
                isSendSongBoxDisplayed = false
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(screenName = "Musiques", listElements = elementsNavBar,  navFunc = onGoHome)
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                data -> SnackbarManager(data)
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                TextField(
                    value = searchText,
                    onValueChange = mainViewModel::onSearchTextChange,
                    label = { Text("Rechercher") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium),
                    shape = MaterialTheme.shapes.small,
                    colors = textFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                )

                if (songs?.isNotEmpty() == true) {
                    ExpandableCard(
                        songListMappedByCategories = songs!!,
                        isSendSongBoxDisplayed = isSendSongBoxDisplayed,
                        registerRequestedSong = { song -> mainViewModel.songRequested.value = song },
                        displaySendSongBox = { newDisplay -> isSendSongBoxDisplayed = !newDisplay }
                    )
                }
            }


            if (isSendSongBoxDisplayed && mainViewModel.songRequested.value != null && clientName != null) {
                SendSongBox(
                    clientName = clientName,
                    song = mainViewModel.songRequested.value!!,
                    mainViewModel = mainViewModel,
                    showSnackBarError = {
                        coroutineScope.launch{
                            snackbarHostState.showSnackbar("Vous devez entrer votre nom d'abord!", duration = SnackbarDuration.Short)
                        }
                    },
                    hideFormBox = { isSendSongBoxDisplayed = false }
                )
            }

            if (isEditNameDisplayed) {
                EditNameFormBox(
                   // mainViewModel = mainViewModel,
                    hideFormBox = { isEditNameDisplayed = false }
                )
            }

            if (isConnecting) LoadingAnimation()

            if (showConnectionError) coroutineScope.launch{
                snackbarHostState.showSnackbar("Erreur de connexion!", duration = SnackbarDuration.Indefinite)
            }
        }
    )

}


@Composable
fun AddClientName(
    displayEditNameForm: Boolean,
    addFunc: (Boolean) -> Unit
){
    IconButton(onClick = { addFunc(displayEditNameForm) } ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.EditNote,
            contentDescription = "edit client name"
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MusicsScreenPreview(){
//    LocalViewModelStoreOwner = compositionLocalOf { MainViewModel() }
//    CompositionLocalProvider(LocalViewModelStoreOwner provides MainViewModel()) {
//        MusicsScreen(
//            clientName = null,
//            latestSocketState = null,
//            modifyLatestSocketState = {},
//            onGoHome = {}
//        )
//    }
//}

