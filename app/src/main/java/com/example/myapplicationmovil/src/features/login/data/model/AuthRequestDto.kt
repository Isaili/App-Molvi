package com.example.myapplicationmovil.src.features.login.data.model

import com.example.myapplicationmovil.src.features.login.domain.models.AuthRequest

data class AuthRequestDto(
    val email: String,
    val password: String
) {
    fun toDomain() = AuthRequest(email = email, password = password)
}