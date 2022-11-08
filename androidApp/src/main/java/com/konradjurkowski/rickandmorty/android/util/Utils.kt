package com.konradjurkowski.rickandmorty.android.util

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

inline fun Modifier.noRippleClickable(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

inline fun Modifier.vibrateClick(crossinline onClick: ()->Unit): Modifier = composed {
    val haptic = LocalHapticFeedback.current
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        onClick()
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}