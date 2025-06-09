package com.example.myapplicationmovil.src.features.register.data.datasource


import com.example.myapplicationmovil.src.features.register.data.model.AuthRequestDto
import com.example.myapplicationmovil.src.features.register.data.model.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("/api/auth/register")
    suspend fun register(@Body request: AuthRequestDto): AuthResponseDto

}