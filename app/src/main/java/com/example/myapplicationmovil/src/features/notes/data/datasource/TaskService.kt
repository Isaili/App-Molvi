package com.example.myapplicationmovil.src.features.notes.data.datasource

import com.example.myapplicationmovil.src.features.notes.data.models.CreateTaskDto
import com.example.myapplicationmovil.src.features.notes.data.models.DeleteTaskDto
import com.example.myapplicationmovil.src.features.notes.data.models.TaskDto
import com.example.myapplicationmovil.src.features.notes.data.models.UpdateTaskDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface TaskService {

    @GET("/api/notes/{idUser}")
    suspend fun listTask(@Path("idUser") idUser: String): List<TaskDto>

    @POST("/api/notes/")
    suspend fun createTask(@Body request: CreateTaskDto): TaskDto

    @PUT("/api/notes/{id}")
    suspend fun updateTask(@Path("id") id: String, @Body request: UpdateTaskDto): TaskDto

    @DELETE("/api/notes/{id}")
    suspend fun deleteTask(@Path("id") id: String): DeleteTaskDto

}