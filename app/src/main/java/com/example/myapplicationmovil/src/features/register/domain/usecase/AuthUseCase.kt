package com.example.myapplicationmovil.src.features.register.domain.usecase

import com.example.myapplicationmovil.src.features.register.domain.models.AuthRequest
import com.example.myapplicationmovil.src.features.register.domain.models.AuthResponse
import com.example.myapplicationmovil.src.features.register.domain.repository.AuthRepository


class AuthUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(request: AuthRequest): AuthResponse{
        return repository.register(request)
    }

}