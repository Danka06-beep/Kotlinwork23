package com.example.kotlinwork2_1

import io.ktor.client.HttpClient
import io.ktor.client.features.json.*
import io.ktor.client.request.get
import io.ktor.http.ContentType

object Recording {
    val clients = HttpClient{
        install(JsonFeature){
            serializer = GsonSerializer()
            accept(ContentType.Text.Plain, ContentType.Application.Json)
        }
    }

    suspend fun getPosts(): List<Post> =
        clients.get(urlString = "https://raw.githubusercontent.com/Danka06-beep/kotWork22/master/post.json")
    suspend fun getPostAdvertising( ): List<Post> =
        clients.get(urlString = "https://raw.githubusercontent.com/Danka06-beep/kotWork22/master/advertising.json")
}





