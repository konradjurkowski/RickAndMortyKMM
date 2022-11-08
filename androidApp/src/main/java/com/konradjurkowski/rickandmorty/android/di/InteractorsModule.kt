package com.konradjurkowski.rickandmorty.android.di

import com.konradjurkowski.rickandmorty.data.remote.RickAndMortyService
import com.konradjurkowski.rickandmorty.interactors.character_detail.GetCharacter
import com.konradjurkowski.rickandmorty.interactors.character_list.GetCharacterList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Provides
    @Singleton
    fun provideGetCharacterList(
        rickAndMortyService: RickAndMortyService
    ): GetCharacterList {
        return GetCharacterList(rickAndMortyService)
    }

    @Provides
    @Singleton
    fun provideGetCharacter(
        rickAndMortyService: RickAndMortyService
    ): GetCharacter {
        return GetCharacter(rickAndMortyService)
    }
}