package com.example.myapplicationmovil.src.core.di


import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.myapplicationmovil.src.core.hardware.data.CameraFactory
import com.example.myapplicationmovil.src.core.hardware.domain.CamaraRepository

object HardwareModule{
    lateinit var cameraFactory : CamaraRepository
        private set

    fun initCameraFactory(
        context: Context,
        lifecycle: LifecycleOwner,
        previewView: androidx.camera.view.PreviewView
    ){
        cameraFactory = CameraFactory.create(context, lifecycle, previewView)
    }
}