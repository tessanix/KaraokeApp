package com.example.karaokeapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.karaokeapp.data.Song
import com.example.karaokeapp.repository.DataStoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class MainActivityViewModel @JvmOverloads constructor(
    application: Application,
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val database: DatabaseReference = FirebaseDatabase
        .getInstance().getReferenceFromUrl("https://fir-test-a3869-default-rtdb.firebaseio.com/"),
    private val repository: DataStoreRepository = DataStoreRepository(application)
): AndroidViewModel(application) {

    // liveData to manage Admin mode
    var isAdmin = repository.readFromDataStore.asLiveData() //MutableLiveData( readFromDataStore.value ?: false )
    //val isAdmin = _isAdmin
    private fun saveToDataStore(adminPrivilege: Boolean) = viewModelScope.launch(Dispatchers.IO){
        repository.saveToDataStore(adminPrivilege)
//        delay(2000L)
    }

    fun authenticate( id: String, password: String ){
        firebaseAuth.signInWithEmailAndPassword(id, password)//"tessanix51@gmail.com", "Cannelle2")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveToDataStore(adminPrivilege = true)
                    Log.d("Authentication", "signInWithCustomToken: success")
                } else {
                    saveToDataStore(adminPrivilege = false)
                    Log.d("Authentication", "signInWithCustomToken: Failed")
                }
                Log.d("Authentication", "admin value is ${isAdmin.value}")

            }
    }

    fun signOut(){
        saveToDataStore(adminPrivilege = false)
        firebaseAuth.signOut()
        Log.d("User disconnected", "admin value is ${isAdmin.value}")
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


    suspend fun addSongs (
        category: String,
        newTitle : String,
        newAuthor: String
    ) { //= viewModelScope.launch{
        val database: DatabaseReference = FirebaseDatabase.getInstance().getReference(category)

        val resultDatabase = database.get().await()

        val numElements = resultDatabase.children.count()

        database.child(numElements.toString()).setValue(
            Song(title = newTitle, author = newAuthor)
        )
    }

}