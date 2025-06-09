package com.example.myapplicationmovil.src.features.notes.domain.models

import com.example.myapplicationmovil.src.features.notes.data.models.TaskDto

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val date: String
){
    fun toDo() = TaskDto(
        id = id,
        title = title,
        description = description,
        date = date
    )
}