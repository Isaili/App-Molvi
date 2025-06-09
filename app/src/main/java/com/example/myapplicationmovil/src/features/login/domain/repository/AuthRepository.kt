package com.example.myapplicationmovil.src.features.login.domain.repository

import com.example.myapplicationmovil.src.features.login.domain.models.AuthRequest
import com.example.myapplicationmovil.src.features.login.domain.models.AuthResponse

interface AuthRepository {
    suspend fun login(request: AuthRequest): AuthResponse
}