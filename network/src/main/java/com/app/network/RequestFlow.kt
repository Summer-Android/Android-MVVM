package com.app.network

import com.app.network.bean.ApiResponse
import com.app.network.exception.AppException
import com.app.network.exception.ExceptionHandle
import com.app.network.state.ApiResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> requestFlow(
    block: suspend () -> ApiResponse<T>,
) = flow<ApiResponseState<T>> {
    val response = block()
    if (response.isSuccess()) {
        emit(ApiResponseState.SUCCESS(response))
    } else {
        throw response.throwAppException()
    }
}.catch { e ->
    e.printStackTrace()
    emit(ApiResponseState.ERROR(ExceptionHandle.handleException(e)))
}.flowOn(Dispatchers.IO)

fun <T> ApiResponse<T>.throwAppException(): AppException {
    val response = this
    return AppException(
        data = response.getResponseData(),
        msg = response.getResponseMessage(),
        code = response.getResponseCode(),
    )
}