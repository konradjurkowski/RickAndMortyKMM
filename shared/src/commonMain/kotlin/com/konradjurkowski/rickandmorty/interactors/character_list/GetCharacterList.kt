package com.konradjurkowski.rickandmorty.interactors.character_list

import com.konradjurkowski.rickandmorty.data.remote.RickAndMortyService
import com.konradjurkowski.rickandmorty.data.remote.model.character.toCharacterList
import com.konradjurkowski.rickandmorty.domain.model.Character
import com.konradjurkowski.rickandmorty.util.CommonFlow
import com.konradjurkowski.rickandmorty.util.DataState
import com.konradjurkowski.rickandmorty.util.asCommonFlow
import kotlinx.coroutines.flow.flow

class GetCharacterList(
    private val service: RickAndMortyService
) {
    fun execute(
        page: Int,
        query: String,
    ): CommonFlow<DataState<List<Character>>> = flow {
        try {
            emit(DataState.loading())
            val characters = service.getAllCharacters(query, page)
            emit(DataState.data(data = characters.toCharacterList()))
        } catch (exception: Exception) {
            emit(DataState.error(message = "Cannot load characters!"))
        }
    }.asCommonFlow()
}