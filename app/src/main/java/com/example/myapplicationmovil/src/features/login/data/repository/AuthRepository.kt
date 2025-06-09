package com.example.myapplicationmovil.src.features.login.data.repository

import com.example.myapplicationmovil.src.features.login.data.datasource.AuthService
import com.example.myapplicationmovil.src.features.login.domain.models.AuthRequest
import com.example.myapplicationmovil.src.features.login.domain.models.AuthResponse
import com.example.myapplicationmovil.src.features.login.domain.repository.AuthRepository


class AuthRepositoryImpl(private val api: AuthService): AuthRepository{
    override suspend fun login(request: AuthRequest): AuthResponse {
        val response = api.loginUser(request.toDo())
        return  response.toDomain()
    }
}
