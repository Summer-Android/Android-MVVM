package com.summer.module.api

import com.app.network.bean.ApiResponse

data class AppApiResponseImpl<T>(
    val data: T?,
    val code: Int,
    val message: String?,
) : ApiResponse<T> {
    override fun isSuccess(): Boolean = code == 0

    override fun getResponseCode(): Int = code

    override fun getResponseMessage(): String? = message

    override fun getResponseData(): T? = data
}