package com.mizikarocco.karaokeapp.data

import kotlinx.serialization.Serializable

@Serializable
data class WebSocketRequest (
    val action: String,
    val data: ClientRequest //Map<String, String>
)