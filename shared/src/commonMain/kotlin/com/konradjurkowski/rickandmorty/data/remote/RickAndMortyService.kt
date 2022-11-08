package com.konradjurkowski.rickandmorty.data.remote

import com.konradjurkowski.rickandmorty.data.remote.model.character.CharacterDto

interface RickAndMortyService {

    // Characters
    suspend fun getAllCharacters(query: String, page: Int): List<CharacterDto>
    suspend fun getCharacterById(characterId: Int): CharacterDto
}