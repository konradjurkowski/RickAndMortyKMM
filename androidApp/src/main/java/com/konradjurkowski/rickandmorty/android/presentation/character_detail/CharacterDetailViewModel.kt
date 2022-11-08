package com.konradjurkowski.rickandmorty.android.presentation.character_detail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konradjurkowski.rickandmorty.android.presentation.navigation.NavigationArguments.CHARACTER_ID
import com.konradjurkowski.rickandmorty.android.presentation.navigation.NavigationArguments.CHARACTER_NAME
import com.konradjurkowski.rickandmorty.interactors.character_detail.GetCharacter
import com.konradjurkowski.rickandmorty.presentation.character_detail.CharacterDetailEvent
import com.konradjurkowski.rickandmorty.presentation.character_detail.CharacterDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacter: GetCharacter
): ViewModel() {

    var state by mutableStateOf(CharacterDetailState())

    init {
        savedStateHandle.get<Int>(CHARACTER_ID)?.let { characterId ->
            onTriggerEvent(CharacterDetailEvent.GetCharacter(characterId))
        }
        savedStateHandle.get<String>(CHARACTER_NAME)?.let { characterName ->
            state = state.copy(pageTitle = characterName)
        }
    }

    private fun onTriggerEvent(event: CharacterDetailEvent) {
        when(event) {
            is CharacterDetailEvent.GetCharacter -> {
                loadCharacter(event.characterId)
            }
        }
    }

    private fun loadCharacter(characterId: Int) {
        getCharacter
            .execute(characterId)
            .onEach { dataState ->
                state = state.copy(isLoading = dataState.isLoading)

                dataState.data?.let { character ->
                    state = state.copy(character = character)
                }

                dataState.message?.let { message ->
                    state = state.copy(error = message)
                }
            }.launchIn(viewModelScope)
    }
}