package com.konradjurkowski.rickandmorty.android.presentation.navigation

sealed class Screen(
    val route: String
) {
    // Characters
    object CharacterList: Screen("characterList")
    object CharacterDetail: Screen("characterDetail")
    // Locations
    object LocationList: Screen("locationList")
    object LocationDetail: Screen("locationDetail")
    // Episodes
    object EpisodeList: Screen("episodeList")
    object EpisodeDetail: Screen("episodeDetail")
}

object NavigationArguments {
    const val CHARACTER_ID = "character_id"
    const val CHARACTER_NAME = "character_name"
}