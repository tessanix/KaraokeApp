package com.example.karaokeapp.ui.theme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.karaokeapp.data.Song
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class MainActivityViewModel(
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val database: DatabaseReference = FirebaseDatabase
        .getInstance().getReferenceFromUrl("https://fir-test-a3869-default-rtdb.firebaseio.com/")

): ViewModel() {

    private var _isAdmin = MutableLiveData(false)
    val isAdmin : LiveData<Boolean> = _isAdmin

    fun authentificate(){
        //val currentUser = firebaseAuth.currentUser
        //Log.d("User to string ",currentUser.toString())
        //if(currentUser == null) { // User is logged out
            firebaseAuth.signInWithEmailAndPassword("tessanix51@gmail.com", "Cannelle2555")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Authentication", "signInWithCustomToken: success")
                        _isAdmin.value = true
                    } else {
                        Log.d("Authentication", "signInWithCustomToken: Failed")
                        _isAdmin.value = false
                    }
                }
        /*} else{ // User already logged in
            currentUser.getIdToken(true)
                .addOnCompleteListener {
                    Log.d("User was already connected. Current User uid is ", it.result.toString())
                }
            // TODO: if user already logged in, that's means uid == admin uid => should be admin ?
            _isAdmin.value = true*/

        //}

    }

    fun signOut(){
        _isAdmin.value = false
        firebaseAuth.signOut()
        Log.d("User disconnected", "admin value is ${_isAdmin.value}")
    }


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


    suspend fun addSongs(
        category: String,
        newTitle : String,
        newAuthor: String
    ) {
        val database: DatabaseReference = FirebaseDatabase.getInstance().getReference(category)

        val resultDatabase = database.get().await()

        val numElements = resultDatabase.children.count()

        database.child(numElements.toString()).setValue(
            Song(title = newTitle, author = newAuthor)
        )

    }


}