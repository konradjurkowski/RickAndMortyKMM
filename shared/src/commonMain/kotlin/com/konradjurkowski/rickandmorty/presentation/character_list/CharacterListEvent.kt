package com.konradjurkowski.rickandmorty.presentation.character_list

sealed class CharacterListEvent {
    object LoadCharacters: CharacterListEvent()
    object NewPage: CharacterListEvent()
    object NewSearch: CharacterListEvent()
    data class OnUpdateQuery(val query: String): CharacterListEvent()
}