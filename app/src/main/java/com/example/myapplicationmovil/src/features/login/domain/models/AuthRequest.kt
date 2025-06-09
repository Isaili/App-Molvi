package com.example.myapplicationmovil.src.features.login.domain.models

import com.example.myapplicationmovil.src.features.login.data.model.AuthRequestDto

data class AuthRequest(
    val email: String,
    val password: String
){
    fun toDo() = AuthRequestDto(email, password)
}