package com.app.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.build()
        return chain.proceed(builder.build())
    }
}