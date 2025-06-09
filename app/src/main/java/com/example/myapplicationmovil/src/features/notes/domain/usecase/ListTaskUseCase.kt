package com.example.myapplicationmovil.src.features.notes.domain.usecase

import com.example.myapplicationmovil.src.features.notes.domain.models.Task
import com.example.myapplicationmovil.src.features.notes.domain.repository.TaskRepository

class ListTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(id: String): List<Task>{
        return repository.getAll(id)
    }
}