package com.konradjurkowski.rickandmorty.domain.model

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: Gender,
    val id: Int,
    val image: String,
    val location: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: Status,
    val type: String
)

enum class Gender(
    val value: String
) {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    companion object {
        fun safeValueOf(type: String?): Gender = values().find { it.value == type } ?: UNKNOWN
    }
}

enum class Status(
    val value: String
) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("unknown");

    companion object {
        fun safeValueOf(type: String?): Status= values().find { it.value == type } ?: UNKNOWN
    }
}
