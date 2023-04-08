package com.mizikarocco.karaokeapp.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

//name of preferences file that will be stored that persist even if app stops
const val PREFERENCE_PRIVILEGE = "my_preference"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCE_PRIVILEGE
)

class DataStoreRepository(private val context: Context,
) {

    private object PreferenceKeys {
        val adminKey = booleanPreferencesKey("isAdmin")
        val clientNameKey = stringPreferencesKey("clientName")

    }

    suspend fun saveToDataStore(isAdminPreference: Boolean) {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.adminKey] = isAdminPreference
        }
    }

    suspend fun saveToDataStore(clientNamePreference: String) {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.clientNameKey] = clientNamePreference
        }
    }

    val data = context.dataStore
        .data
        .catch { exception ->
            if (exception is IOException) {
                exception.message?.let { stringMessage ->
                    Log.d("DataStore", stringMessage)
                }
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }

    val readAdminFromDataStore: Flow<Boolean> = data
        .map { preferences ->
            val adminPrivilege = preferences[PreferenceKeys.adminKey] ?: false
            adminPrivilege
        }

    val readClientNameFromDataStore: Flow<String>  = data
        .map { preferences ->
            val adminPrivilege = preferences[PreferenceKeys.clientNameKey] ?: ""
            adminPrivilege
        }

}