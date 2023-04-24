package com.mizikarocco.karaokeapp.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ServerKaraokeApi(private val client: HttpClient) : ServerApi{

    private var session: WebSocketSession? = null

    override fun onReceiveFromSocket(): Flow<WebSocketResponse> {
        return flow {
            session = client.webSocketSession(HttpRoutes.request)

            val responseFromSocket = session!!
                .incoming
                .consumeAsFlow()
                .filterIsInstance<Frame.Text>()
                .mapNotNull { Json.decodeFromString<WebSocketResponse>(it.readText()) }

            emitAll(responseFromSocket)
        }
    }


    override suspend fun sendClientRequest(request: ClientRequest) {
        session?.outgoing?.send(
            Frame.Text(
                Json.encodeToString(
                    WebSocketRequest(
                        action = "addRequest",
                        data = request
                    )
                )
            )
        )
    }

    override suspend fun close() {
        session?.close()
        session = null
    }

    override fun getAllSongs(): Flow<Map<String, List<Song>>> {
        return flow {
            emit(client.get(HttpRoutes.songs).body())
        }

//        } catch(e: RedirectResponseException){
//            println("Error: ${e.response.status.description}")
//            emptyMap()
//        } catch(e: ClientRequestException) {
//            println("Error: ${e.response.status.description}")
//            emptyMap()
//        } catch(e: ServerResponseException) {
//            println("Error: ${e.response.status.description}")
//            emptyMap()
//        } catch(e: Exception) {
//            println("Error: ${e.message}")
//            emptyMap()
//        }
    }


}