package com.konradjurkowski.rickandmorty.di

import com.konradjurkowski.rickandmorty.interactors.character_detail.GetCharacter
import com.konradjurkowski.rickandmorty.interactors.character_list.GetCharacterList

object InteractorsModule {
    val getCharacter: GetCharacter by lazy {
        GetCharacter(
            NetworkModule.rickAndMortyService
        )
    }

    val getCharacterList: GetCharacterList by lazy {
        GetCharacterList(
            NetworkModule.rickAndMortyService
        )
    }
}