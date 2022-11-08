//
//  CharacterView.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct CharacterView: View {
    
    private let character: Character
    
    init(character: Character) {
        self.character = character
    }
    
    var body: some View {
        ScrollView {
            Text("Appearance")
                .font(Font.subheadline)
                .padding(.leading, 16)
                .padding(.trailing, 16)
                .foregroundColor(.gray)
                .frame(maxWidth: .infinity, alignment: .leading)
            
            WebImage(url: URL(string: character.image))
                .resizable()
                .placeholder(Image(systemName: "photo"))
                .placeholder {
                    Rectangle().foregroundColor(.white)
                }
                .indicator(.activity)
                .transition(.fade(duration: 0.5))
                .scaledToFill()
                .frame(maxWidth: .infinity, alignment: .leading)
                .clipped()
                .cornerRadius(8)
                .padding(16)
            
            Text("Info")
                .font(Font.subheadline)
                .padding(.leading, 16)
                .padding(.trailing, 16)
                .foregroundColor(.gray)
                .frame(maxWidth: .infinity, alignment: .leading)
            
            VStack {
                InfoItem(
                    leadingIcon: "waveform.path.ecg",
                    leadingText: "Status",
                    trailingText: character.status.value
                )
                InfoItem(
                    leadingIcon: "brain.head.profile",
                    leadingText: "Species",
                    trailingText: character.species
                )
                InfoItem(
                    leadingIcon: "figure.stand",
                    leadingText: "Gender",
                    trailingText: character.gender.value
                )
                InfoItem(
                    leadingIcon: "location",
                    leadingText: "Location",
                    trailingText: character.location
                )
                InfoItem(
                    leadingIcon: "film",
                    leadingText: "Episodes",
                    trailingText: "\(character.episode.count)"
                )
            }
            .background(Color.gray)
            .cornerRadius(8)
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(16)
        }
    }
}
