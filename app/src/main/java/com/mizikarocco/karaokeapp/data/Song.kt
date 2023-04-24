package com.mizikarocco.karaokeapp.data
import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val id: String,
    val title : String,
    val author : String
){
    fun doesMatchSearchQuery(query: String) : Boolean {
        val matchingCombinations = listOf(
            "$title$author",
            "$title $author",
            "${if(title.isNotBlank()) title.first() else ""}  ${if(author.isNotBlank()) author.first() else ""}",
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}

