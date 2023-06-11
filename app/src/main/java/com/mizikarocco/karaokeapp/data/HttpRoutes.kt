package com.mizikarocco.karaokeapp.data

object HttpRoutes {
    private const val IP_ADDR = "10.188.200.203"
    private const val BASE_URL = "http://$IP_ADDR:8080"
    const val songs = "$BASE_URL/musics"


    private const val BASE_SOCKET_URL = "ws://$IP_ADDR:8080"
    const val request = "$BASE_SOCKET_URL/websocketrequestspage"
}
