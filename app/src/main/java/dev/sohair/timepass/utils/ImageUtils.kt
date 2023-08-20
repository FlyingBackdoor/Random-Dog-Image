package dev.sohair.timepass.utils

import dev.sohair.timepass.data.model.ImageResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object ImageUtils {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun getRandomImage(): ImageResult {
        return client.get("https://dog.ceo/api/breeds/image/random").body()
    }
}