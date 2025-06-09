package com.example.myapplicationmovil.src.features.login.domain.usecase

import com.example.myapplicationmovil.src.features.login.domain.models.AuthRequest
import com.example.myapplicationmovil.src.features.login.domain.models.AuthResponse
import com.example.myapplicationmovil.src.features.login.domain.repository.AuthRepository

class AuthUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(request: AuthRequest): AuthResponse{
        return repository.login(request)
    }

}