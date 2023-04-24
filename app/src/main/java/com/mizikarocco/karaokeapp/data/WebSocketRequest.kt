package com.mizikarocco.karaokeapp.data

import kotlinx.serialization.Serializable

@Serializable
data class WebSocketRequest (
    val action: String,
    val data: Map<String, String>
)