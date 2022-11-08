//
//  CharacterListScreen.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharacterListScreen: View {
    
    // dependencies
    private let interactorsModule = InteractorsModule()
    
    @ObservedObject var viewModel: CharacterListViewModel
    
    init() {
        self.viewModel = CharacterListViewModel(
            getCharacterList: interactorsModule.getCharacterList)
    }
    
    var body: some View {
        NavigationView {
            ZStack {
                    VStack {
                        Image("ic_logo")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 200)
                        InputTextField(
                            query: viewModel.state.query,
                            onTriggerEvent: viewModel.onTriggerEvent
                        )
                        .padding(.bottom, 8)
                        if (viewModel.state.error != nil && !viewModel.state.isLoading) {
                            Spacer()
                            Text(viewModel.state.error!)
                                .font(Font.headline)
                                .multilineTextAlignment(.center)
                                .padding(8)
                            Spacer()
                        } else {
                            List {
                                ForEach(viewModel.state.characters, id: \.self.id) { character in
                                    CharacterCard(character: character)
                                        .overlay(NavigationLink(
                                            destination: CharacterDetailScreen(
                                                getCharacter: interactorsModule.getCharacter,
                                                characterId: character.id,
                                                characterName: character.name
                                            )
                                            , label: {
                                            EmptyView()
                                        }))
                                        .onAppear(perform: {
                                            if (viewModel.shouldQueryNextPage(character: character)) {
                                                viewModel.onTriggerEvent(event: CharacterListEvent.NewPage())
                                            }
                                        })
                                        .listRowInsets(EdgeInsets())
                                        .padding(.top, 10)
                                        .padding(.leading, 16)
                                        .padding(.trailing, 16)
            
                                }
                            }
                            .listStyle(PlainListStyle())
                        }
                        Spacer()
                    }
                if (viewModel.state.isLoading && viewModel.state.characters.isEmpty) {
                    ProgressView("Searching characters...")
                }
            }
            .navigationBarHidden(true)
            .alert(isPresented: $viewModel.showDialog, content: {
                return ErrorAlertDialog().build(
                    message: viewModel.state.error,
                    removeMessage: viewModel.removeErrorMessage
                )
            })
        }
    }
}
