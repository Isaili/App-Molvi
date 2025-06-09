package com.example.myapplicationmovil.src.features.register.data.model

import com.example.myapplicationmovil.src.features.register.domain.models.AuthResponse


data class AuthResponseDto(
    val token: String,
    val message: String,
    val user: UserDto
){
    fun toDomain() = AuthResponse(
        token = token,
        message = message,
        user = user.toDomain()
    )
}