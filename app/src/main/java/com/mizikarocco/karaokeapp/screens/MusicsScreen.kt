package com.mizikarocco.karaokeapp.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mizikarocco.karaokeapp.MainActivityViewModel
import com.mizikarocco.karaokeapp.MusicsViewModel
import com.mizikarocco.karaokeapp.components.*



@Composable
fun MusicsScreen (
    isAdmin: Boolean?,
    clientName: String?,
    onGoHome : () -> Unit
) {
    val mainViewModel : MainActivityViewModel = viewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)
    val musicsViewModel = viewModel<MusicsViewModel>()

    val searchText by musicsViewModel.searchText.collectAsState()
    val songs by musicsViewModel.songs.collectAsState()
    val isSearching by musicsViewModel.isSearching.collectAsState()
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



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        NavBarReturnButton( listElements = elementsNavBar, navFunc = onGoHome)
        
        TextField(
            value = searchText,
            onValueChange = musicsViewModel::onSearchTextChange,
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
            musicsViewModel = musicsViewModel
        ){ isSendSongBoxDisplayed = false }
    }

    if(isEditNameDisplayed){
        EditNameFormBox(mainViewModel = mainViewModel){
            isEditNameDisplayed = false
        }
    }

    if(isFormBoxDisplayed){
        FormBox(musicsViewModel = musicsViewModel){
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
