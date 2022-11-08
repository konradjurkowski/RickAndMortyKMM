package com.konradjurkowski.rickandmorty.presentation.character_detail

sealed class CharacterDetailEvent {
    data class GetCharacter(val characterId: Int): CharacterDetailEvent()
}