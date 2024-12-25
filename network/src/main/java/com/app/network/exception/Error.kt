package com.app.network.exception

import androidx.annotation.StringRes
import com.app.application.BaseApplication
import com.app.network.R

/**
 * 错误枚举类
 */
enum class Error(
    private val code: Int,
    @StringRes private val msg: Int
) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, R.string.error_unknown),

    /**
     * 解析错误
     */
    PARSE_ERROR(1001, R.string.error_parse),

    /**
     * 网络错误
     */
    NETWORK_ERROR(1002, R.string.error_network),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, R.string.error_ssl),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, R.string.error_timeout);


    fun getCode(): Int {
        return code
    }

    fun getMsg(): String? {
        return BaseApplication.instance.getString(msg)
    }
}