package com.konradjurkowski.rickandmorty.android.di

import com.konradjurkowski.rickandmorty.data.remote.KtorClientFactory
import com.konradjurkowski.rickandmorty.data.remote.RickAndMortyService
import com.konradjurkowski.rickandmorty.data.remote.RickAndMortyServiceImpl
import com.konradjurkowski.rickandmorty.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory()
            .build(Constants.API_BASE_URL)
    }

    @Provides
    @Singleton
    fun provideRickAndMortyService(
        httpClient: HttpClient
    ): RickAndMortyService {
        return RickAndMortyServiceImpl(
            httpClient = httpClient
        )
    }
}