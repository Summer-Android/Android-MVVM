package com.app.network.exception

import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException

import retrofit2.HttpException
import java.net.ConnectException
import java.text.ParseException
import javax.net.ssl.SSLException

object ExceptionHandle {

    fun handleException(throwable: Throwable?): AppException {
        val ex: AppException
        throwable?.let { exception ->
            when (exception) {
                is HttpException -> {
                    ex = AppException(code = Error.NETWORK_ERROR.getCode(), msg = Error.NETWORK_ERROR.getMsg(), throwable = throwable)
                    return ex
                }

                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = AppException(code = Error.PARSE_ERROR.getCode(), msg = Error.PARSE_ERROR.getMsg(), throwable = throwable)
                    return ex
                }

                is ConnectException -> {
                    ex = AppException(code = Error.NETWORK_ERROR.getCode(), msg = Error.NETWORK_ERROR.getMsg(), throwable = throwable)
                    return ex
                }

                is SSLException -> {
                    ex = AppException(code = Error.SSL_ERROR.getCode(), msg = Error.SSL_ERROR.getMsg(), throwable = throwable)
                    return ex
                }

                is ConnectTimeoutException -> {
                    ex = AppException(code = Error.TIMEOUT_ERROR.getCode(), msg = Error.TIMEOUT_ERROR.getMsg(), throwable = throwable)
                    return ex
                }

                is java.net.SocketTimeoutException -> {
                    ex = AppException(code = Error.TIMEOUT_ERROR.getCode(), msg = Error.TIMEOUT_ERROR.getMsg(), throwable = throwable)
                    return ex
                }

                is java.net.UnknownHostException -> {
                    ex = AppException(code = Error.TIMEOUT_ERROR.getCode(), msg = Error.TIMEOUT_ERROR.getMsg(), throwable = throwable)
                    return ex
                }

                is AppException -> return exception
                else -> {
                    ex = AppException(code = Error.UNKNOWN.getCode(), msg = Error.UNKNOWN.getMsg(), throwable = throwable)
                    return ex
                }
            }
        }
        ex = AppException(code = Error.UNKNOWN.getCode(), msg = Error.UNKNOWN.getMsg(), throwable = null)
        return ex
    }
}