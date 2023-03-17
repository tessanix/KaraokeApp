package com.example.karaokeapp.screens

import androidx.lifecycle.ViewModel
import com.example.karaokeapp.data.Song
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class MusicsViewModel(
    private val database: DatabaseReference = FirebaseDatabase
        .getInstance().getReferenceFromUrl("https://fir-test-a3869-default-rtdb.firebaseio.com/")
) : ViewModel() {

    suspend fun getSongs(): Map<String, List<Song>> {
        val resultDatabase = database.get().await()

        return resultDatabase.children.associate { cat ->
            val songList = cat.children.map { song ->
                Song(
                    title = song.child("title").value.toString(),
                    author = song.child("author").value.toString()
                )
            }
            cat.key.toString() to songList
        }
    }

//    var songs = flow {
//        val resultDatabase = database.get().await()
//        val myMusicsList = mutableMapOf<String, MutableList<Song>>()
//
//        for(cat in resultDatabase.children) {
//            myMusicsList[cat.key.toString()] = mutableListOf()
//            for (song in cat.children) {
//                myMusicsList[cat.key.toString()]?.add(
//                    Song(
//                        title = song.child("title").value.toString(),
//                        author = song.child("author").value.toString()
//                    )
//                )
//            }
//        }
//        emit(myMusicsList)
//    }
}