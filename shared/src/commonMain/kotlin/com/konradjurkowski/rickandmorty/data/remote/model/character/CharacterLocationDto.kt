package com.konradjurkowski.rickandmorty.data.remote.model.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocationDto(
    val name: String,
    val url: String
)