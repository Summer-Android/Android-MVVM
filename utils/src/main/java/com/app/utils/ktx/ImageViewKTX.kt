package com.app.utils.ktx

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.ImageViewCompat
import com.google.android.material.imageview.ShapeableImageView

fun ImageView.setImageTint(@ColorRes color: Int) {
    AppCompatResources.getColorStateList(context, color)?.let { colorStateList ->
        ImageViewCompat.setImageTintList(this, colorStateList)
    }
}

/* ---------------- 设置 ShapeableImageView 圆角 -------------- */

fun ShapeableImageView.setAllCornerSizes(cornerSize: Float) {
    this.shapeAppearanceModel = this.shapeAppearanceModel
        .toBuilder()
        .setAllCornerSizes(cornerSize)
        .build()
}

fun ShapeableImageView.setTopLeftCornerSize(cornerSize: Float) {
    this.shapeAppearanceModel = this.shapeAppearanceModel
        .toBuilder()
        .setTopLeftCornerSize(cornerSize)
        .build()
}

fun ShapeableImageView.setTopRightCornerSize(cornerSize: Float) {
    this.shapeAppearanceModel = this.shapeAppearanceModel
        .toBuilder()
        .setTopRightCornerSize(cornerSize)
        .build()
}

fun ShapeableImageView.setBottomLeftCornerSize(cornerSize: Float) {
    this.shapeAppearanceModel = this.shapeAppearanceModel
        .toBuilder()
        .setBottomLeftCornerSize(cornerSize)
        .build()
}

fun ShapeableImageView.setBottomRightCornerSize(cornerSize: Float) {
    this.shapeAppearanceModel = this.shapeAppearanceModel
        .toBuilder()
        .setBottomRightCornerSize(cornerSize)
        .build()
}
