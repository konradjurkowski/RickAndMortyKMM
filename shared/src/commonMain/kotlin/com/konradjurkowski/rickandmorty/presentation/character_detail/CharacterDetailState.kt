package com.konradjurkowski.rickandmorty.presentation.character_detail

import com.konradjurkowski.rickandmorty.domain.model.Character

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val pageTitle: String = "",
    val error: String? = null
) {
    constructor(): this(
        isLoading = false,
        character = null,
        pageTitle = "",
        error = null
    )
}