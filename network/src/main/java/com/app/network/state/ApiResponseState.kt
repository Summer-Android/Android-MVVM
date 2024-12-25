package com.app.network.state

import com.app.network.bean.ApiResponse
import com.app.network.exception.AppException

sealed class ApiResponseState<out T> {
    data class SUCCESS<T>(val success: ApiResponse<T>) : ApiResponseState<T>()
    data class ERROR(val error: AppException) : ApiResponseState<Nothing>()
}