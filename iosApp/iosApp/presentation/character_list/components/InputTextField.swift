//
//  InputTextField.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct InputTextField: View {
    
    @State var query: String = ""
    private let onTriggerEvent: (CharacterListEvent) -> Void
    
    init(
        query: String,
        onTriggerEvent: @escaping (CharacterListEvent) -> Void) {
        self._query = State(initialValue: query)
        self.onTriggerEvent = onTriggerEvent
    }
    
    var body: some View {
        HStack {
            Image(systemName: "magnifyingglass")
            TextField(
                "Search...",
                text: $query,
                onCommit: {
                    onTriggerEvent(CharacterListEvent.NewSearch())
                }
            )
            .font(Font.title2)
            .onChange(
                of: query,
                perform: { value in
                    onTriggerEvent(CharacterListEvent.OnUpdateQuery(query: value))
                }
            )
        }
        .padding(.top, 8)
        .padding(.leading, 8)
        .padding(.trailing, 8)
        .background(Color.white.opacity(0))
    }
}
