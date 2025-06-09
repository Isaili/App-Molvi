package com.example.myapplicationmovil.src.features.register.data.model

import com.example.myapplicationmovil.src.features.register.domain.models.User


data class UserDto(
    val id: String,
    val email: String,
    val username: String
) {
    fun toDomain() = User(
        id = id,
        email = email,
        username = username

    )
}