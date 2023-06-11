package com.mizikarocco.karaokeapp.di

import android.content.Context
import com.mizikarocco.karaokeapp.data.ServerKaraokeApi
import com.mizikarocco.karaokeapp.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // allow Module to stay alive since the app is alive
object AppModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStoreRepository {
        return DataStoreRepository(appContext)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO){
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }
        }
    }

    @Provides
    @Singleton // only create 1 instance of module in the entire app
    fun provideServerApi(httpClient:HttpClient): ServerKaraokeApi {
        return ServerKaraokeApi(httpClient)
    }

}