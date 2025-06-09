package com.example.myapplicationmovil.src.features.notes.domain.models

import com.example.myapplicationmovil.src.features.notes.data.models.UpdateTaskDto

data class UpdateTask(
    val title: String,
    val description: String,
){
    fun toDo() = UpdateTaskDto(
        title = title,
        description = description
    )
}