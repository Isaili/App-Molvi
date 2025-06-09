package com.example.myapplicationmovil.src.features.login.data.datasource

import com.example.myapplicationmovil.src.features.login.data.model.AuthRequestDto
import com.example.myapplicationmovil.src.features.login.data.model.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("/api/auth/login")
    suspend fun loginUser(@Body request: AuthRequestDto): AuthResponseDto

}