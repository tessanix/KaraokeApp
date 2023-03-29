package com.mizikarocco.karaokeapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mizikarocco.karaokeapp.MainActivityViewModel
import com.mizikarocco.karaokeapp.components.ExpandableCard
import com.mizikarocco.karaokeapp.components.FormBox
import com.mizikarocco.karaokeapp.components.NavBarReturnButton
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.ui.theme.AppTheme
import com.mizikarocco.karaokeapp.ui.theme.Orientation


@Composable
fun MusicsScreen (
    mainViewModel: MainActivityViewModel,
    isAdmin: Boolean?,
    songs : Map<String, List<Song>>,
    onGoHome : () -> Unit
) {
    var isFormBoxDisplayed by remember { mutableStateOf(false) }

    val elementsNavBar = if(isAdmin == true) {
        listOf<@Composable () -> Unit> {
            AddSongButton(isFormBoxDisplayed) { newDisplay ->
                isFormBoxDisplayed = !newDisplay
            }
        }
    } else {
        emptyList()
    }

    if (AppTheme.orientation == Orientation.Portrait) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            NavBarReturnButton(isPortrait = true, listElements = elementsNavBar, navFunc = onGoHome)

            if (songs.isNotEmpty()) {
                ExpandableCard(songs, true)
            }
        }

    } else{
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            NavBarReturnButton(isPortrait = false, listElements = elementsNavBar, navFunc = onGoHome)

            if(songs.isNotEmpty()){
                ExpandableCard(songs, false)
            }
        }
    }


    if(isFormBoxDisplayed){
        FormBox(mainViewModel = mainViewModel){
            isFormBoxDisplayed = false
        }
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
