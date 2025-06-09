package com.example.myapplicationmovil.src.features.register.data.repository

import com.example.myapplicationmovil.src.features.register.data.datasource.AuthService
import com.example.myapplicationmovil.src.features.register.domain.models.AuthRequest
import com.example.myapplicationmovil.src.features.register.domain.models.AuthResponse
import com.example.myapplicationmovil.src.features.register.domain.repository.AuthRepository


class AuthRepositoryImpl(private val api: AuthService): AuthRepository{
    override suspend fun register(request: AuthRequest): AuthResponse {
        val response = api.register(request.toDo())
        return  response.toDomain()
    }
}
