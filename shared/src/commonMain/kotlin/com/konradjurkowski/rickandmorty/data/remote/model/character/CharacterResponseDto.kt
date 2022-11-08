package com.konradjurkowski.rickandmorty.data.remote.model.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)