package com.mizikarocco.karaokeapp.data

object HttpRoutes {
    private const val BASE_URL = "http://192.168.0.11:8080"
    const val songs = "$BASE_URL/musics"


    private const val BASE_SOCKET_URL = "ws://192.168.0.11:8080"
    const val request = "$BASE_SOCKET_URL/websocketrequestspage"
}
