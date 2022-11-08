//
//  CharacterCard.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct CharacterCard: View {
    
    private let character: Character
    
    init(character: Character) {
        self.character = character
    }
    
    var body: some View {
        HStack {
            WebImage(url: URL(string: character.image))
                .resizable()
                .placeholder(Image(systemName: "photo"))
                .placeholder {
                    Rectangle().foregroundColor(.white)
                }
                .indicator(.activity)
                .transition(.fade(duration: 0.5))
                .scaledToFill()
                .frame(width: 100, height: 100, alignment: .leading)
                .clipped()
                .cornerRadius(8)
                .padding(8)
            
            VStack {
                Text(character.name)
                    .font(Font.headline)
                    .lineLimit(1)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("Status: \(character.status.value)")
                    .font(Font.headline)
                    .lineLimit(1)
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            .padding(.top, 8)
            .padding(.leading, 8)
            .padding(.trailing, 8)
            .padding(.bottom, 12)
        }
        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
        .background(Color.white)
        .cornerRadius(8)
        .shadow(radius: 5)
    }
}
