//
//  CharacterDetailViewModel.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class CharacterDetailViewModel: ObservableObject {
    
    // dependencies
    private let getCharacter: GetCharacter
    
    @Published var state: CharacterDetailState = CharacterDetailState()
    
    @Published var showDialog: Bool = false
    
    init(
        charactrId: Int32,
        getCharacter: GetCharacter
    ) {
        self.getCharacter = getCharacter
        onTriggerEvent(event: CharacterDetailEvent.GetCharacter(characterId: charactrId))
    }
    
    func onTriggerEvent(event: CharacterDetailEvent) {
        switch event {
            case is CharacterDetailEvent.GetCharacter:
            loadCharacter(characterId: (event as! CharacterDetailEvent.GetCharacter).characterId)
            default: ()
        }
    }
    
    func removeErrorMessage() {
        self.updateState(error: nil)
    }
    
    private func loadCharacter(characterId: Int32) {
        getCharacter
            .execute(characterId: characterId)
            .watch(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading
                        
                        self.updateState(isLoading: loading)
                        
                        if data != nil {
                            self.updateState(character: data! as Character)
                        }
                        
                        if message != nil {
                            self.updateState(error: message)
                        }
                    } else {
                        self.updateState(error: "Cannot load character!")
                    }
                })
    }
    
    private func updateState(
        isLoading: Bool? = nil,
        character: Character? = nil,
        error: String? = nil
    ) {
        state = state.doCopy(
            isLoading: isLoading ?? state.isLoading,
            character: character ?? state.character,
            pageTitle: "",
            error: error ?? state.error
        )
        shouldShowDialog()
    }
    
    private func shouldShowDialog() {
        showDialog = state.error != nil
    }
}
