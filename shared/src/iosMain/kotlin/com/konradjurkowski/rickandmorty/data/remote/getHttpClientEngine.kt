package com.konradjurkowski.rickandmorty.data.remote

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun getHttpClientEngine(): HttpClientEngine {
    return Darwin.create()
}