package com.mizikarocco.karaokeapp.screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mizikarocco.karaokeapp.MainActivityViewModel
import com.mizikarocco.karaokeapp.components.*
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.ui.theme.AppTheme
import com.mizikarocco.karaokeapp.ui.theme.Orientation


@Composable
fun MusicsScreen (
    mainViewModel: MainActivityViewModel,
    isAdmin: Boolean?,
    clientName: String?,
    songs : Map<String, List<Song>>,
    onGoHome : () -> Unit
) {
    //val mainViewModel = LocalViewModelStoreOwner.current
//    val clientNametest = currentCompositionLocalContext
//    Log.d("clientName", clientNametest.toString())
    var isFormBoxDisplayed by remember { mutableStateOf(false) }
    var isEditNameDisplayed by remember { mutableStateOf(false) }
    var isSendSongBoxDisplayed by remember { mutableStateOf(false) }


    val elementsNavBar = if(isAdmin == true) {
        listOf<@Composable () -> Unit> {
            AddSongButton(isFormBoxDisplayed) { newDisplay ->
                isFormBoxDisplayed = !newDisplay
            }

            AddClientName(isEditNameDisplayed) { newDisplay ->
                isEditNameDisplayed = !newDisplay
            }
        }
    } else {
        listOf<@Composable () -> Unit> {
            AddClientName(isEditNameDisplayed) { newDisplay ->
                isEditNameDisplayed = !newDisplay
            }
        }
    }


    if (AppTheme.orientation == Orientation.Portrait) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            NavBarReturnButton(isPortrait = true, listElements = elementsNavBar, navFunc = onGoHome)

            if (songs.isNotEmpty()) {
                ExpandableCard(
                    songs,
                    true,
                    isSendSongBoxDisplayed,
                    { song -> mainViewModel.songRequested.value = song }
                ){ newDisplay -> isSendSongBoxDisplayed = !newDisplay }
            }
        }

    } else{
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            NavBarReturnButton(isPortrait = false, listElements = elementsNavBar, navFunc = onGoHome)

            if(songs.isNotEmpty()){
                ExpandableCard(
                    songs,
                    false,
                    isSendSongBoxDisplayed,
                    { song -> mainViewModel.songRequested.value = song }
                ) { newDisplay -> isSendSongBoxDisplayed = !newDisplay }
            }
        }
    }

    if(isSendSongBoxDisplayed && mainViewModel.songRequested.value != null && clientName != null){
        SendSongBox(
            clientName = clientName!!,
            song = mainViewModel.songRequested.value!!,
            mainViewModel = mainViewModel
        ){ isSendSongBoxDisplayed = false }
    }

    if(isEditNameDisplayed){
        EditNameFormBox(mainViewModel = mainViewModel){
            isEditNameDisplayed = false
        }
    }

    if(isFormBoxDisplayed){
        FormBox(mainViewModel = mainViewModel){
            isFormBoxDisplayed = false
        }
    }

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

@Composable
fun AddSongButton(
    displayForm: Boolean,
    addFunc: (Boolean) -> Unit
){
    IconButton(onClick = { addFunc(displayForm) } ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "add song"
        )
    }
}

//@Composable
//@Preview(showBackground = true)
//fun MusicsScreenPreview(){
//    MusicsScreen{ }
//}
