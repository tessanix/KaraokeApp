package com.mizikarocco.karaokeapp.data


data class Song(
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

