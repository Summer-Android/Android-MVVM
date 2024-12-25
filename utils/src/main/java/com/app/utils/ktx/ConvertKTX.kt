package com.app.utils.ktx

import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.ConvertUtils

inline val Float.dp
    get() = ConvertUtils.dp2px(this)
inline val Float.sp
    get() = ConvertUtils.sp2px(this)
inline val Float.pt
    get() = AdaptScreenUtils.pt2Px(this)

inline val Int.dp
    get() = ConvertUtils.dp2px(this.toFloat())
inline val Int.sp
    get() = ConvertUtils.sp2px(this.toFloat())
inline val Int.pt
    get() = AdaptScreenUtils.pt2Px(this.toFloat())