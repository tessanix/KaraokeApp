package com.mizikarocco.karaokeapp.data

import kotlinx.coroutines.flow.Flow

interface ServerApi {

    fun getAllSongs() : Flow<Map<String, List<Song>>>

    suspend fun sendClientRequest(clientName:String, request: ClientRequest)
    suspend fun close()

    fun onReceiveFromSocket(): Flow<WebSocketResponse>

}