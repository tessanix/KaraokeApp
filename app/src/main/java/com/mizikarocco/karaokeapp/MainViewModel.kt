package com.mizikarocco.karaokeapp


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mizikarocco.karaokeapp.data.ClientRequest
import com.mizikarocco.karaokeapp.data.ServerKaraokeApi
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.data.WebSocketResponse
import com.mizikarocco.karaokeapp.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ServerKaraokeApi,
    private val repository: DataStoreRepository
): ViewModel() {

    var clientName = repository.readClientNameFromDataStore.asLiveData()

    fun saveToDataStore(clientName: String) = viewModelScope.launch(Dispatchers.IO){
        repository.saveToDataStore(clientName)
    }

    var socketState = api
        .onReceiveFromSocket()
        .onEach {  _isConnecting.value = false }
        .catch { t -> _showConnectionError.value = t is ConnectException }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    var previousSocketState: WebSocketResponse? = null

    val songRequested = MutableLiveData<Song>()

    private val _isConnecting = MutableStateFlow(false)
    val isConnecting = _isConnecting.asStateFlow()

    private val _showConnectionError = MutableStateFlow(false)
    val showConnectionError = _showConnectionError.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    private val _songs = api.getAllSongs()
        .onStart { _isConnecting.value = true }
        .onCompletion { _isConnecting.value = false }
        .catch { t -> _showConnectionError.value = t is ConnectException }

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

    fun sendClientRequest(clientName:String, clientRequest: ClientRequest) {
        viewModelScope.launch {
            _isConnecting.value = true
            api.sendClientRequest(clientName, clientRequest)
        }
    }
    override fun onCleared(){
        viewModelScope.launch { api.close() }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}