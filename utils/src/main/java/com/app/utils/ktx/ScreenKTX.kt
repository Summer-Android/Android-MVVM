package com.app.utils.ktx

import com.blankj.utilcode.util.ScreenUtils

/** 获取屏幕宽度 */
inline val screenWidth
    get() = ScreenUtils.getScreenWidth()

/** 获取屏幕高度 */
inline val screenHeight
    get() = ScreenUtils.getScreenHeight()
