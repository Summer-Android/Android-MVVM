package com.summer.module.repository

import com.app.network.requestFlow
import com.app.network.state.ApiResponseState
import com.summer.module.api.AppApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val appApiService: AppApiService
) {

    fun fetchData(): Flow<ApiResponseState<List<Any>>> {
        return requestFlow { appApiService.fetchData() }
    }
}