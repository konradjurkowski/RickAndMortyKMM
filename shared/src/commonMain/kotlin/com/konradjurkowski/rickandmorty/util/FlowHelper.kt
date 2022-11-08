package com.konradjurkowski.rickandmorty.util

import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    fun watch(
        coroutineScope: CoroutineScope? = null,
        callback: (T) -> Unit
    ): Closeable {
        val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

        onEach {
            callback(it)
        }.launchIn(coroutineScope ?: scope)

        return object : Closeable {
            override fun close() {
                scope.cancel()
            }
        }
    }
}