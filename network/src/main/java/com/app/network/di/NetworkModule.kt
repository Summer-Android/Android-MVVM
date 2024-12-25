package com.app.network.di

import android.util.Log
import com.app.network.http.log.Level
import com.app.network.http.log.LoggingInterceptor
import com.app.network.interceptor.HeadInterceptor
import com.hjq.gson.factory.GsonFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.example.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 64
        dispatcher.maxRequestsPerHost = 5
        return OkHttpClient.Builder().apply {
            dispatcher(dispatcher)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HeadInterceptor())
            // 日志拦截器
            addInterceptor(
                // log：https://github.com/ihsanbal/LoggingInterceptor
                LoggingInterceptor.Builder()
                    .setLevel(Level.BASIC)
                    .tag("Http")
                    .request("HTTP-请求")
                    .response("HTTP-响应")
                    .log(Log.INFO)
                    .build()
            )
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))
            .build()
    }
}