//
//  CharacterDetailScreen.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharacterDetailScreen: View {
    
    private let getCharacter: GetCharacter
    private let characterId: Int32
    private let characterName: String
    
    @ObservedObject var viewModel: CharacterDetailViewModel
    
    init(getCharacter: GetCharacter, characterId: Int32, characterName: String) {
        self.getCharacter = getCharacter
        self.characterId = characterId
        self.characterName = characterName
        viewModel = CharacterDetailViewModel(
            charactrId: characterId,
            getCharacter: getCharacter
        )
    }
    
    var body: some View {
        VStack {
            Text(characterName)
                .font(Font.title2)
            if (viewModel.state.character == nil && viewModel.state.isLoading) {
                ProgressView("Loading character data...")
            } else if (viewModel.state.error != nil && !viewModel.state.isLoading) {
                Spacer()
                Text(viewModel.state.error!)
                    .font(Font.headline)
                    .multilineTextAlignment(.center)
                    .padding(8)
                Spacer()
            } else {
                CharacterView(character: viewModel.state.character!)
            }
        }
    }
}
