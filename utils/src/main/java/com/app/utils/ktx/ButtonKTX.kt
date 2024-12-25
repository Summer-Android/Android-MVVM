package com.app.utils.ktx

import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.button.MaterialButton

fun MaterialButton.setIconTint(@ColorRes color: Int) {
    AppCompatResources.getColorStateList(context, color)?.let { colorStateList ->
        iconTint = colorStateList
    }
}

fun MaterialButton.setBackgroundTint(@ColorRes color: Int) {
    AppCompatResources.getColorStateList(context, color)?.let { colorStateList ->
        backgroundTintList = colorStateList
    }
}

fun MaterialButton.setRippleColor(@ColorRes color: Int) {
    AppCompatResources.getColorStateList(context, color)?.let { colorStateList ->
        rippleColor = colorStateList
    }
}