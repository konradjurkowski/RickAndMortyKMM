package com.konradjurkowski.rickandmorty.data.remote

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorClientFactory {
    fun build(baseUrl: String): HttpClient {
        return HttpClient(getHttpClientEngine()) {
            defaultRequest {
                url(baseUrl)
            }
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }
    }
}

expect fun getHttpClientEngine(): HttpClientEngine