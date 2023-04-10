package com.mizikarocco.karaokeapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.mizikarocco.karaokeapp.data.Song
import com.mizikarocco.karaokeapp.repository.DataStoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mizikarocco.karaokeapp.data.ClientRequest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class MainActivityViewModel @JvmOverloads constructor(
    application: Application,
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
//    private val databaseRoot: DatabaseReference = FirebaseDatabase
//        .getInstance()
//        .getReferenceFromUrl("https://fir-test-a3869-default-rtdb.firebaseio.com/"),
    private val repository: DataStoreRepository = DataStoreRepository(application)
): AndroidViewModel(application) {

    val songRequested = MutableLiveData<Song>()

    // liveData to manage Admin mode
    var isAdmin = repository.readAdminFromDataStore.asLiveData()
    private fun saveToDataStore(adminPrivilege: Boolean) = viewModelScope.launch(Dispatchers.IO){
        repository.saveToDataStore(adminPrivilege)
    }

    var clientName = repository.readClientNameFromDataStore.asLiveData()
    fun saveToDataStore(clientName: String) = viewModelScope.launch(Dispatchers.IO){
        repository.saveToDataStore(clientName)
    }

    fun authenticate( id: String, password: String, callbackError: (Boolean) -> Unit){
        firebaseAuth.signInWithEmailAndPassword(id, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveToDataStore(adminPrivilege = true)
                    Log.d("Authentication", "signInWithCustomToken: success")
                    callbackError(false)
                } else {
                    saveToDataStore(adminPrivilege = false)
                    Log.d("Authentication", "signInWithCustomToken: Failed")
                    callbackError(true)
                }
                Log.d("Authentication", "admin value is ${isAdmin.value}")

            }
    }


    fun signOut(){
        saveToDataStore(adminPrivilege = false)
        firebaseAuth.signOut()
        Log.d("User disconnected", "admin value is ${isAdmin.value}")
    }

//    suspend fun getSongs(): Map<String, List<Song>> {
//        //val resultDatabase = database.child("Musics").get().await()
//
//        val resultDatabase = databaseRoot.child("musics").get().await()
//
//        return resultDatabase.children.associate { cat ->
//            val songList = cat.children.map { song ->
//                Song(
//                    title = song.child("title").value.toString(),
//                    author = song.child("author").value.toString()
//                )
//            }
//            cat.key.toString() to songList
//        }
//
//    }
//
//
//    suspend fun addSongs (
//        category: String,
//        newTitle : String,
//        newAuthor: String
//    ) {
//        val databaseCategory = databaseRoot.child("musics").child(category)
//        val resultDatabase = databaseCategory.get().await()
//
//        val numElements = resultDatabase.children.count()
//
//        databaseCategory.child(numElements.toString()).setValue(
//            Song(title = newTitle, author = newAuthor)
//        )
//    }



}