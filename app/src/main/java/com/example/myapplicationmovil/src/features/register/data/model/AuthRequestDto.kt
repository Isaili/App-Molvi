package com.example.myapplicationmovil.src.features.register.data.model

import com.example.myapplicationmovil.src.features.register.domain.models.AuthRequest


data class AuthRequestDto(
    val username: String,
    val email: String,
    val password: String
) {
    fun toDomain() = AuthRequest( username= username, email = email, password = password)
}