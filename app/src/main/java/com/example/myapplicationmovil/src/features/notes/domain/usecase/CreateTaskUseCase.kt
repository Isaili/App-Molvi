package com.example.myapplicationmovil.src.features.notes.domain.usecase

import com.example.myapplicationmovil.src.features.notes.domain.models.CreateTask
import com.example.myapplicationmovil.src.features.notes.domain.models.Task
import com.example.myapplicationmovil.src.features.notes.domain.repository.TaskRepository

class CreateTaskUseCase(private val repository: TaskRepository){
    suspend operator fun invoke(request: CreateTask): Task{
        return repository.create(request)
    }
}