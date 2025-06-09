package com.example.myapplicationmovil.src.features.login.data.model

import com.example.myapplicationmovil.src.features.login.domain.models.AuthResponse


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