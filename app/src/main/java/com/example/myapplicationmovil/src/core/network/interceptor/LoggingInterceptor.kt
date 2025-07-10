package com.example.myapplicationmovil.src.core.interceptor

import okhttp3.logging.HttpLoggingInterceptor

fun  provideLoggingInterceptor(): HttpLoggingInterceptor{
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}