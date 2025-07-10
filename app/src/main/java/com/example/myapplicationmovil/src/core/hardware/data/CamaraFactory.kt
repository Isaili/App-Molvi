package com.example.myapplicationmovil.src.core.hardware.data

import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.camera.core.impl.CameraRepository
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.myapplicationmovil.src.core.hardware.domain.CamaraRepository

object CameraFactory {
    fun create(
        context: Context,
        lifecycle: LifecycleOwner,
        previewView: PreviewView
    ): CamaraRepository {
        return CamaraManager(context, lifecycle, previewView)
    }
}