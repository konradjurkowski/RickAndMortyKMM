package com.konradjurkowski.rickandmorty.interactors.character_detail

import com.konradjurkowski.rickandmorty.data.remote.RickAndMortyService
import com.konradjurkowski.rickandmorty.data.remote.model.character.toCharacter
import com.konradjurkowski.rickandmorty.domain.model.Character
import com.konradjurkowski.rickandmorty.util.CommonFlow
import com.konradjurkowski.rickandmorty.util.DataState
import com.konradjurkowski.rickandmorty.util.asCommonFlow
import kotlinx.coroutines.flow.flow

class GetCharacter(
    private val service: RickAndMortyService
) {
    fun execute(
        characterId: Int
    ): CommonFlow<DataState<Character>> = flow {
        try {
            emit(DataState.loading())
            val character = service.getCharacterById(characterId)
            emit(DataState.data(data = character.toCharacter()))
        } catch (exception: Exception) {
            emit(DataState.error(message = "Cannot load Character with id: $characterId"))
        }
    }.asCommonFlow()
}