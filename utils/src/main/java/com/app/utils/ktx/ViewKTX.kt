package com.app.utils.ktx

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.isVisible

@Suppress("NOTHING_TO_INLINE")
inline fun View.visible() {
    visibility = View.VISIBLE
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.gone() {
    visibility = View.GONE
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.invisible() {
    visibility = View.INVISIBLE
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.toggleVisibility() {
    visibility = if (isVisible) View.GONE else View.VISIBLE
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.toggleInvisibility() {
    visibility = if (isVisible) View.INVISIBLE else View.VISIBLE
}

inline fun View.visibleIf(function: () -> Boolean) {
    visibility = if (function.invoke()) View.VISIBLE else View.GONE
}

inline fun View.invisibleIf(function: () -> Boolean) {
    visibility = if (function.invoke()) View.INVISIBLE else View.VISIBLE
}

inline fun View.goneIf(function: () -> Boolean) {
    visibility = if (function.invoke()) View.GONE else View.VISIBLE
}

/**
 * 防止重复点击事件 默认0.5秒内不可重复点击
 * @param interval 时间间隔 默认0.5秒
 * @param onClick 执行方法
 */
fun View.setOnclickNoRepeat(interval: Long = 500L, onClick: (View) -> Unit) {
    var lastClickTime = 0L

    this.setOnClickListener { view ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= interval) {
            lastClickTime = currentTime
            onClick(view)
        }
    }
}

/**
 * 圆形波纹
 */
fun View.setRippleOval(
    @ColorInt rippleColor: Int,
    @ColorInt shapeColor: Int = Color.TRANSPARENT,
    @ColorInt maskColor: Int = Color.WHITE
) {
    this.setRipple(
        cornerRadius = 99f,
        rippleColor = rippleColor,
        shapeColor = shapeColor,
        maskColor = maskColor
    )
}

/**
 * 矩形波纹效果设置
 *
 * - `cornerRadius`: 统一设置所有角的圆角半径（可选）。
 *   - 如果设置了 `cornerRadius`，则将其应用于所有四个角
 *
 *   等价代码:
 *   ```kotlin
 *   floatArrayOf(
 *       cornerRadius, cornerRadius,  // 左上角
 *       cornerRadius, cornerRadius,  // 右上角
 *       cornerRadius, cornerRadius,  // 右下角
 *       cornerRadius, cornerRadius   // 左下角
 *   )
 *   ```
 *
 * - `cornerRadii`: 单独设置每个角的圆角半径（可选）。如果提供了 `cornerRadii`，则优先使用此参数。
 * - 数组顺序:`[左上角X, 左上角Y, 右上角X, 右上角Y, 右下角X, 右下角Y, 左下角X, 左下角Y]`
 *
 * @param rippleColor 涟漪效果的颜色。
 * @param shapeColor 背景形状的颜色，默认为透明。
 * @param maskColor 掩膜形状的颜色，控制涟漪的范围，默认为白色。
 */
fun View.setRipple(
    cornerRadius: Float? = null,
    cornerRadii: FloatArray? = null,
    @ColorInt rippleColor: Int,
    @ColorInt shapeColor: Int = Color.TRANSPARENT,
    @ColorInt maskColor: Int = Color.WHITE
) {
    // 生成圆角数组
    val radii = cornerRadii ?: if (cornerRadius != null) {
        floatArrayOf(
            cornerRadius, cornerRadius,  // 左上角
            cornerRadius, cornerRadius,  // 右上角
            cornerRadius, cornerRadius,  // 右下角
            cornerRadius, cornerRadius,  // 左下角
        )
    } else {
        floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    }

    val shapeDrawable = ShapeDrawable().apply {
        shape = RoundRectShape(radii, null, null)
        paint.color = shapeColor
    }

    val maskDrawable = ShapeDrawable().apply {
        shape = RoundRectShape(radii, null, null)
        paint.color = maskColor
    }

    background = RippleDrawable(ColorStateList.valueOf(rippleColor), shapeDrawable, maskDrawable)
}