package com.example.myapplicationmovil.src.features.register.domain.models

import com.example.myapplicationmovil.src.features.register.data.model.AuthRequestDto


data class AuthRequest(
    val username: String,
    val email: String,
    val password: String
){
    fun toDo() = AuthRequestDto(
        username = username,
        email = email,
        password = password
    )
}