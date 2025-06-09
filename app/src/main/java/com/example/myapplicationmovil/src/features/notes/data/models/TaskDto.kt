package com.example.myapplicationmovil.src.features.notes.data.models

import com.example.myapplicationmovil.src.features.notes.domain.models.Task

data class TaskDto(
    val id: String,
    val title: String,
    val description: String,
    val date: String
){
    fun  toDomain() = Task(
        id = id,
        title = title,
        description = description,
        date = date
    )
}