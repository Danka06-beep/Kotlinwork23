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
        clients.get(urlString = "http://127.0.0.1:8080/api/v1/posts")
    suspend fun getPostAdvertising( ): List<Post> =
        clients.get(urlString = "http://127.0.0.1:8080/api/v1/posts")


}





