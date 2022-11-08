package com.konradjurkowski.rickandmorty.data.remote

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun getHttpClientEngine(): HttpClientEngine {
    return OkHttp.create()
}