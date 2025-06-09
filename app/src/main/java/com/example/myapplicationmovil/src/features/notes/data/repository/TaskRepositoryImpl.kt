package com.example.myapplicationmovil.src.features.notes.data.repository

import com.example.myapplicationmovil.src.features.notes.data.datasource.TaskService
import com.example.myapplicationmovil.src.features.notes.domain.models.CreateTask
import com.example.myapplicationmovil.src.features.notes.domain.models.Task
import com.example.myapplicationmovil.src.features.notes.domain.models.UpdateTask
import com.example.myapplicationmovil.src.features.notes.domain.repository.TaskRepository

class TaskRepositoryImpl (private val api: TaskService): TaskRepository{

    override suspend fun getAll(idUser: String): List<Task> {
       val response = api.listTask(idUser)
        return  response.map { it.toDomain() }
    }

    override suspend fun create(request: CreateTask): Task {
       val response = api.createTask(request.toDo())
        return response.toDomain()
    }

    override suspend fun update(id: String, request: UpdateTask): Task {
       val response = api.updateTask(id, request.toDo())
        return  response.toDomain()
    }

    override suspend fun delete(id: String): String {
      val response = api.deleteTask(id)
        return response.msg
    }

}