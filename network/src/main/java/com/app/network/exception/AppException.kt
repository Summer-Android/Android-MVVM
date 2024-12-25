package com.app.network.exception

import com.app.application.BaseApplication
import com.app.network.R

/**
 * 自定义错误信息异常
 */
data class AppException(
    val data: Any? = null,
    val code: Int = -1,
    private val msg: String? = null,
    val status: Boolean = false,
    private val throwable: Throwable? = null,
) : Exception(
    msg ?: BaseApplication.instance.getString(R.string.error_unknown),
    throwable
)