package com.example.myapplicationmovil.src.features.login.domain.models

data class AuthResponse(
    val token: String,
    val message: String,
    val user: User
)