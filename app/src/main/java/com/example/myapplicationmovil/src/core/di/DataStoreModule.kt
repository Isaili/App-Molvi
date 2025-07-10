package com.example.myapplicationmovil.src.core.di

import com.example.myapplicationmovil.src.core.appcontext.AppContextHolder
import com.example.myapplicationmovil.src.core.store.DataStoreManager

object DataStoreModule{
    val dataStoreManager: DataStoreManager by lazy {
        DataStoreManager(AppContextHolder.get())
    }
}