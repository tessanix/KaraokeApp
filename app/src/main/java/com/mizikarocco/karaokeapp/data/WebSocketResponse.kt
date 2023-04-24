package com.mizikarocco.karaokeapp.data

import kotlinx.serialization.Serializable


@Serializable
data class WebSocketResponse(
    val action: String,
    val status : String,
    val data: Map<String, String> = emptyMap()
)