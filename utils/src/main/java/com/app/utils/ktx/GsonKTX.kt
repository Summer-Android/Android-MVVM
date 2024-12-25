package com.app.utils.ktx

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hjq.gson.factory.GsonFactory

/**
 * 注意json解析混淆问题
 */

inline val gson: Gson get() = GsonFactory.getSingletonGson()

fun Any?.toJson(): String = if (this == null) "" else gson.toJson(this)

inline fun <reified T> String.fromJson(): T? {
    return try {
        gson.fromJson(this, T::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

inline fun <reified T> String.fromJsonList(): List<T>? {
    return try {
        val type = TypeToken.getParameterized(List::class.java, T::class.java).type
        gson.fromJson(this, type) as? List<T>
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}