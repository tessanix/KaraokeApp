package com.mizikarocco.karaokeapp.data

import kotlinx.serialization.Serializable

@Serializable
data class ClientRequest(
    //val songId: String,
    val clientName: String,
    val title : String,
    val author: String
)
