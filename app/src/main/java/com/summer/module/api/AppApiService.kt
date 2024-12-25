package com.summer.module.api

import retrofit2.http.GET

interface AppApiService {

    @GET("endpoint")
    suspend fun fetchData(): AppApiResponseImpl<List<Any>>

}