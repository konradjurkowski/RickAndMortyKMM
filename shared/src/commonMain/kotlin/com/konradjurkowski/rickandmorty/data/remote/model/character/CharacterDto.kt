package com.konradjurkowski.rickandmorty.data.remote.model.character

import com.konradjurkowski.rickandmorty.domain.model.Character
import com.konradjurkowski.rickandmorty.domain.model.Gender
import com.konradjurkowski.rickandmorty.domain.model.Status
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterLocationDto,
    val name: String,
    val origin: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterDto.toCharacter(): Character {
    return Character(
        created = this.created,
        episode = this.episode,
        gender = Gender.safeValueOf(this.gender),
        id = this.id,
        image = this.image,
        location = this.location.name,
        name = this.name,
        origin = this.origin.name,
        species = this.species,
        status = Status.safeValueOf(this.status),
        type = this.type
    )
}

fun List<CharacterDto>.toCharacterList(): List<Character> {
    return map { it.toCharacter() }
}