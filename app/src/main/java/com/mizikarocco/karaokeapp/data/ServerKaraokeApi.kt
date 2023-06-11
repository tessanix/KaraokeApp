package com.mizikarocco.karaokeapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
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