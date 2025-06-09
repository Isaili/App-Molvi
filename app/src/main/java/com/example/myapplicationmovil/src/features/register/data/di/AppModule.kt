package com.example.myapplicationmovil.src.features.register.data.di

import com.example.myapplicationmovil.src.core.network.RetrofitHelper
import com.example.myapplicationmovil.src.features.register.data.datasource.AuthService
import com.example.myapplicationmovil.src.features.register.data.repository.AuthRepositoryImpl
import com.example.myapplicationmovil.src.features.register.domain.repository.AuthRepository
import com.example.myapplicationmovil.src.features.register.domain.usecase.AuthUseCase


object AppModule {
    private val api: AuthService = RetrofitHelper.retrofit.create(AuthService::class.java)

    private val  repository: AuthRepository = AuthRepositoryImpl(api)

    val auhthUseCase = AuthUseCase(repository)

}