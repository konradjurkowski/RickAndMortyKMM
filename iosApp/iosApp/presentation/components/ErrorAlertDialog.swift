//
//  ErrorAlertDialog.swift
//  iosApp
//
//  Created by Konrad Jurkowski on 08/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ErrorAlertDialog {
    
    func build(
        message: String?,
        removeMessage: @escaping () -> Void
    ) -> Alert {
        return Alert(
            title: Text("Error"),
            message: Text(message ?? "Something went wrong"),
            primaryButton: .default(
                Text("Ok"),
                action: {
                    removeMessage()
                }
            ),
            secondaryButton: .default(
                Text("Cancel"),
                action: {
                    removeMessage()
                }
            )
        )
    }
}
