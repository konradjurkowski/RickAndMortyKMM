package com.konradjurkowski.rickandmorty.di

import com.konradjurkowski.rickandmorty.data.remote.KtorClientFactory
import com.konradjurkowski.rickandmorty.data.remote.RickAndMortyService
import com.konradjurkowski.rickandmorty.data.remote.RickAndMortyServiceImpl
import com.konradjurkowski.rickandmorty.util.Constants

object NetworkModule {
    val rickAndMortyService: RickAndMortyService by lazy {
        RickAndMortyServiceImpl(
            httpClient = KtorClientFactory()
                .build(Constants.API_BASE_URL)
        )
    }
}