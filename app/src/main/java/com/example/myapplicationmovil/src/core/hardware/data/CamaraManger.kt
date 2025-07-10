package com.example.tasks.src.core.hardware.data

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.myapplicationmovil.src.core.hardware.domain.CamaraRepository

import java.io.File
import java.util.UUID
import java.util.concurrent.Executor

class CamaraManager(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val previewView: PreviewView
) : CamaraRepository {

    companion object {
        private const val TAG = "CamaraManager"
        private const val PHOTO_DIR = "camera_photos"
    }

    private val controller = LifecycleCameraController(context)
    private val executor: Executor = ContextCompat.getMainExecutor(context)
    private var isInitialized = false

    init {
        initializeCamera()
    }

    private fun initializeCamera() {
        if (!isInitialized) {
            try {
                controller.setEnabledUseCases(
                    LifecycleCameraController.IMAGE_CAPTURE or
                            LifecycleCameraController.IMAGE_ANALYSIS
                )
                previewView.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
                isInitialized = true
                Log.d(TAG, "Cámara inicializada correctamente")
            } catch (e: Exception) {
                Log.e(TAG, "Error inicializando cámara: ${e.message}", e)
            }
        } else {
            Log.d(TAG, "Cámara ya inicializada")
        }
    }

    override fun capturePhoto(callback: (Uri?) -> Unit) {
        if (!isInitialized) {
            Log.e(TAG, "Cámara no inicializada")
            callback(null)
            return
        }

        try {
            val internalDir = File(context.filesDir, PHOTO_DIR).apply {
                if (!exists()) mkdirs()
            }

            val file = File(internalDir, "IMG_${UUID.randomUUID()}.jpg")
            val output = ImageCapture.OutputFileOptions.Builder(file).build()

            controller.takePicture(
                output,
                executor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(result: ImageCapture.OutputFileResults) {
                        val uri = result.savedUri ?: Uri.fromFile(file)
                        Log.d(TAG, "Foto guardada: $uri")
                        callback(uri)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.e(TAG, "Error capturando foto: ${exception.message}", exception)
                        callback(null)
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error en capturePhoto: ${e.message}", e)
            callback(null)
        }
    }

   override fun getSavedPhoto(): File? {
        val internalDir = File(context.filesDir, PHOTO_DIR)
        return internalDir.listFiles()?.firstOrNull()
    }

  override fun cleanup() {
        if (isInitialized) {
            try {
                previewView.controller = null
                isInitialized = false
                Log.d(TAG, "Recursos de cámara limpiados")
            } catch (e: Exception) {
                Log.e(TAG, "Error limpiando recursos: ${e.message}", e)
            }
        }
    }

    fun reinitializeCamera() {
        if (isInitialized) cleanup()
        initializeCamera()
    }
}