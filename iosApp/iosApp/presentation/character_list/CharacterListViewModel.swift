//
//  CharacterListViewModel.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class CharacterListViewModel: ObservableObject {
    
    // dependencies
    private let getCharacterList: GetCharacterList
    
    @Published var state: CharacterListState = CharacterListState()
    
    @Published var showDialog: Bool = false
    
    init(getCharacterList: GetCharacterList) {
        self.getCharacterList = getCharacterList
        onTriggerEvent(event: CharacterListEvent.LoadCharacters())
    }
    
    func onTriggerEvent(event: CharacterListEvent) {
        switch event {
            case is CharacterListEvent.LoadCharacters:
                loadCharacters()
            case is CharacterListEvent.NewSearch:
                newSearch()
            case is CharacterListEvent.NewPage:
                nextPage()
            case is CharacterListEvent.OnUpdateQuery:
                onUpdateQuery(query: (event as! CharacterListEvent.OnUpdateQuery).query)
            default: ()
        }
    }
    
    func shouldQueryNextPage(character: Character) -> Bool {
        if (character.id == state.bottomCharacter?.id) {
            if (Constants().PAGE_SIZE * state.page <= state.characters.count) {
                if (!state.isLoading) {
                    return true
                }
            }
        }
        return false
    }
    
    func removeErrorMessage() {
        self.updateState(error: nil)
    }
    
    private func nextPage() {
        updateState(page: Int(state.page) + 1)
        loadCharacters()
    }
    
    private func newSearch() {
        resetSearchState()
        loadCharacters()
    }
    
    private func onUpdateQuery(query: String) {
        updateState(query: query)
    }
    
    private func loadCharacters() {
        getCharacterList.execute(
            page: Int32(state.page),
            query: state.query
        ).watch(
            coroutineScope: nil,
            callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false

                    self.updateState(isLoading: loading)

                    if data != nil {
                        self.appendCharacters(characters: data as! [Character])
                    }

                    if message != nil {
                        self.updateState(error: message)
                    }
                } else {
                    self.updateState(error: "Character List are empty!")
                }
            })
    }
    
    private func resetSearchState() {
        state = state.doCopy(
            isLoading: state.isLoading,
            page: 1,
            query: state.query,
            characters: [],
            bottomCharacter: nil,
            error: nil
        )
    }
    
    private func appendCharacters(characters: [Character]) {
        var currentCharacters = state.characters
        currentCharacters.append(contentsOf: characters)
        state = state.doCopy(
            isLoading: state.isLoading,
            page: state.page,
            query: state.query,
            characters: currentCharacters,
            bottomCharacter: state.bottomCharacter,
            error: state.error
        )
        self.updateState(
            bottomCharacter: state.characters[state.characters.count - 1]
        )
    }
    
    private func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        error: String? = nil,
        bottomCharacter: Character? = nil
    ) {
        state = state.doCopy(
            isLoading: isLoading ?? state.isLoading,
            page: Int32(page ?? Int(state.page)),
            query: query ?? state.query,
            characters: state.characters,
            bottomCharacter: bottomCharacter ?? state.bottomCharacter,
            error: error ?? state.error
        )
        shouldShowDialog()
    }
    
    private func shouldShowDialog() {
        showDialog = state.error != nil
    }
}
