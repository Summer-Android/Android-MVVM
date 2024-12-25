package com.app.application

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication

        /** 应用全局协程作用域 **/
        val coroutineScope by lazy { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}