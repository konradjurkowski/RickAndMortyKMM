//
//  InfoItem.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct InfoItem: View {
    
    private let leadingIcon: String
    private let leadingText: String
    private let trailingText: String
    
    init(leadingIcon: String, leadingText: String, trailingText: String) {
        self.leadingIcon = leadingIcon
        self.leadingText = leadingText
        self.trailingText = trailingText
    }
    
    var body: some View {
        HStack {
            HStack {
                Image(systemName: leadingIcon)
                    .foregroundColor(Color.white)
                Text(leadingText)
                    .foregroundColor(Color.white)
            }
            .frame(alignment: .leading)
            Spacer()
            Text(trailingText)
                .frame(alignment: .trailing)
                .foregroundColor(Color.orange)
        }
        .padding(8)
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}
