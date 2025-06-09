package com.example.myapplicationmovil.src.features.register.domain.repository

import com.example.myapplicationmovil.src.features.register.domain.models.AuthRequest
import com.example.myapplicationmovil.src.features.register.domain.models.AuthResponse


interface AuthRepository {
    suspend fun register(request: AuthRequest): AuthResponse
}