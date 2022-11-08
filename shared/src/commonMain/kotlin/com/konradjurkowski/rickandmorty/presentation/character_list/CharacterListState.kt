package com.konradjurkowski.rickandmorty.presentation.character_list

import com.konradjurkowski.rickandmorty.domain.model.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val characters: List<Character> = emptyList(),
    val bottomCharacter: Character? = null,
    val error: String? = null
) {
    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        characters = emptyList(),
        bottomCharacter = null,
        error = null
    )
}
