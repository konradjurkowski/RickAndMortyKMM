package com.konradjurkowski.rickandmorty.data.remote

import com.konradjurkowski.rickandmorty.data.remote.model.character.CharacterDto
import com.konradjurkowski.rickandmorty.data.remote.model.character.CharacterResponseDto
import com.konradjurkowski.rickandmorty.util.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RickAndMortyServiceImpl(
    private val httpClient: HttpClient
): RickAndMortyService {

    override suspend fun getAllCharacters(query: String, page: Int): List<CharacterDto> {
        return httpClient
            .get(Constants.CHARACTER_ENDPOINT) {
                parameter(Constants.NAME_KEY, query)
                parameter(Constants.PAGE_KEY, page)
            }
            .body<CharacterResponseDto>()
            .results
    }

    override suspend fun getCharacterById(characterId: Int): CharacterDto {
        return httpClient
            .get("${Constants.CHARACTER_ENDPOINT}/$characterId")
            .body()
    }
}