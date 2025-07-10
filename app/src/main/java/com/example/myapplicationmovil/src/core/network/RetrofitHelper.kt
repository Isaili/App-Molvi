package com.example.myapplicationmovil.src.core.network

import com.example.myapplicationmovil.src.core.store.DataStoreManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private const val BASE_URL = "https://tasks.wildroid.space"
    private const val TIMEOUT = 20L

    @Volatile
    private var retrofit: Retrofit? = null
    private var dataStoreManager: DataStoreManager? = null

    fun init(dataStore: DataStoreManager, extraInterceptors: List<Interceptor> = emptyList()) {
        dataStoreManager = dataStore
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    retrofit = buildRetrofit(extraInterceptors)
                }
            }
        }
    }

    fun <T> getService(serviceClass: Class<T>): T {
        return requireNotNull(retrofit) {
            "RetrofitHelper no ha sido inicializado. Llama a init() antes de usar getService()."
        }.create(serviceClass)
    }

    private fun buildRetrofit(extraInterceptors: List<Interceptor>): Retrofit {
        val client = buildHttpClient(extraInterceptors)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buildHttpClient(extraInterceptors: List<Interceptor>): OkHttpClient {
        val manager = requireNotNull(dataStoreManager) {
            "DataStoreManager no está disponible. Inicializa RetrofitHelper con init()."
        }

        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(AddTokenInterceptor(manager))
            .addInterceptor(TokenCaptureInterceptor(manager))
            .addInterceptor(provideLoggingInterceptor())
            .apply {
                extraInterceptors.forEach { addInterceptor(it) }
            }
            .build()
    }
}