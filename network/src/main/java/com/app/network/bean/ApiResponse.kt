package com.app.network.bean

interface ApiResponse<T> {
    fun isSuccess(): Boolean
    fun getResponseCode(): Int
    fun getResponseMessage(): String?
    fun getResponseData(): T?
}