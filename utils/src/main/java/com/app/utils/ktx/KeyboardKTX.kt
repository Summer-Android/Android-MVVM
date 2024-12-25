package com.app.utils.ktx

import android.app.Activity
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils

/**
 * 隐藏软键盘
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Activity.hideSoftInput() {
    if (KeyboardUtils.isSoftInputVisible(this)) {
        KeyboardUtils.hideSoftInput(this)
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun View.hideSoftInput() {
    KeyboardUtils.hideSoftInput(this)
}

/**
 * 显示软键盘
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Activity.showSoftInput(view: View) {
    if (!KeyboardUtils.isSoftInputVisible(this)) {
        KeyboardUtils.showSoftInput(view)
    }
}

/**
 * 显示软键盘
 */
@Suppress("NOTHING_TO_INLINE")
inline fun View.showSoftInput() {
    KeyboardUtils.showSoftInput(this)
}