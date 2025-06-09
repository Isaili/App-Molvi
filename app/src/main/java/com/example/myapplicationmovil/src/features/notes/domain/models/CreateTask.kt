package com.example.myapplicationmovil.src.features.notes.domain.models

import com.example.myapplicationmovil.src.features.notes.data.models.CreateTaskDto

data class CreateTask(
    val user_id: String,
    val title: String,
    val description: String
){
    fun toDo() = CreateTaskDto(
        user_id = user_id,
        title = title,
        description = description
    )
}