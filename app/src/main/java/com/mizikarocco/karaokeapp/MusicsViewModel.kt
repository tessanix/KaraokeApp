package com.mizikarocco.karaokeapp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mizikarocco.karaokeapp.data.ClientRequest
import com.mizikarocco.karaokeapp.data.Song
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import kotlinx.coroutines.tasks.await

class MusicsViewModel(
    private val databaseRoot: DatabaseReference = FirebaseDatabase
        .getInstance()
        .getReferenceFromUrl("https://fir-test-a3869-default-rtdb.firebaseio.com/"),
): ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _songs = flow{ emit(getSongs()) }
    val songs = searchText
        .combine(_songs) { text, mapSongs ->
            if(text.isBlank()) mapSongs
            else {
                val mapQuery = mapSongs.mapValues { (_, songs) ->
                    songs.filter{ it.doesMatchSearchQuery(text) }
                }
                mapQuery.filter { (_, songs) -> songs.isNotEmpty() }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _songs.asLiveData().value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private suspend fun getSongs(): Map<String, List<Song>> {

        val resultDatabase = databaseRoot.child("musics").get().await()

        return resultDatabase.children.associate { cat ->
            val songList = cat.children.map { song ->
                Song(
                    title = song.child("title").value.toString(),
                    author = song.child("author").value.toString()
                )
            }
            cat.key.toString() to songList
        }

    }


    suspend fun addSongs (
        category: String,
        newTitle : String,
        newAuthor: String
    ) {
        val databaseCategory = databaseRoot.child("musics").child(category)
        val resultDatabase = databaseCategory.get().await()

        val numElements = resultDatabase.children.count()

        databaseCategory.child(numElements.toString()).setValue(
            Song(title = newTitle, author = newAuthor)
        )
    }

    fun addClientRequest (
        clientName: String,
        song: Song
    ) {
        viewModelScope.launch {
            val databaseClientRequests = databaseRoot.child("clientRequests")
            val resultDatabase = databaseClientRequests.get().await()

            val numElements = resultDatabase.children.count()

            databaseClientRequests.child(numElements.toString()).setValue(
                ClientRequest(clientName, song)
            )
        }
    }

}