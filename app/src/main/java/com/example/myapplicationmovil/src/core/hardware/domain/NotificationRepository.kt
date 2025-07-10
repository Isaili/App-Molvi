package com.example.myapplicationmovil.src.core.hardware.domain

interface NotificationRepository{
    fun activeNotification(idTask: String, taskTitle: String)
}