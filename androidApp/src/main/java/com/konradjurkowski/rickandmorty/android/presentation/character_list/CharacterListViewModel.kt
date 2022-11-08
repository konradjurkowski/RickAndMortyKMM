package com.konradjurkowski.rickandmorty.android.presentation.character_list

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konradjurkowski.rickandmorty.interactors.character_list.GetCharacterList
import com.konradjurkowski.rickandmorty.presentation.character_list.CharacterListEvent
import com.konradjurkowski.rickandmorty.presentation.character_list.CharacterListState
import com.konradjurkowski.rickandmorty.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterList: GetCharacterList
): ViewModel() {

    var state by mutableStateOf(CharacterListState())
        private set

    init {
        onTriggerEvents(CharacterListEvent.LoadCharacters)
    }

    fun onTriggerEvents(event: CharacterListEvent) {
        when (event) {
            CharacterListEvent.LoadCharacters -> {
                loadCharacters()
            }
            CharacterListEvent.NewPage -> {
                nextPage()
            }
            CharacterListEvent.NewSearch -> {
                newSearch()
            }
            is CharacterListEvent.OnUpdateQuery -> {
                state = state.copy(query = event.query)
            }
        }
    }

    /**
     * Search characters by query
     */
    private fun newSearch() {
        state = state.copy(page = 1, characters = emptyList())
        loadCharacters()
    }

    /**
     * Get the next page of characters
     */
    private fun nextPage() {
        state = state.copy(page = state.page + 1)
        loadCharacters()
    }

    /**
     * Get characters from Rick And Morty Api
     */
    private fun loadCharacters() {
        state = state.copy(error = null)
        getCharacterList.execute(
            page = state.page,
            query = state.query
        ).onEach { dataState ->
            state = state.copy(isLoading = dataState.isLoading)

            dataState.data?.let { characters ->
                appendCharacters(characters)
            }

            dataState.message?.let { message ->
                state = state.copy(error = message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendCharacters(characters: List<Character>) {
        val currentList = ArrayList(state.characters)
        currentList.addAll(characters)
        state = state.copy(characters = currentList, isLoading = false)
    }
}