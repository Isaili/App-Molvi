package com.example.myapplicationmovil.src.core.network.interceptor

import com.example.myapplicationmovil.src.core.store.DataStoreManager
import com.example.myapplicationmovil.src.core.store.PreferenceKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class TokenCaptureInterceptor(
    private val datastore: DataStoreManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val authHeader = response.header("Authorization")
        if (!authHeader.isNullOrEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                datastore.saveKey(PreferenceKeys.TOKEN, authHeader)
            }
        }
        return response
    }
}