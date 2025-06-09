package com.example.myapplicationmovil.src.features.notes.data.models

import com.example.myapplicationmovil.src.features.notes.domain.models.CreateTask

data class CreateTaskDto(
    val user_id: String,
    val title: String,
    val description: String
) {
    fun  toDomain() = CreateTask(
        user_id = user_id,
        title = title,
        description = description
    )
}