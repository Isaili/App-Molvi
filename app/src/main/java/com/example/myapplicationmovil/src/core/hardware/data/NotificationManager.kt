package com.example.myapplicationmovil.src.core.hardware.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.myapplicationmovil.R
import com.example.myapplicationmovil.src.core.hardware.common.Constants
import com.example.myapplicationmovil.src.core.hardware.domain.NotificationRepository

class NotificationManager(
    private val context: Context
) : NotificationRepository {

    companion object {
        private const val TAG = "NotificationManager"
    }

    override fun activeNotification(idTask: String, taskTitle: String) {
        if (!hasNotificationPermission()) {
            android.util.Log.w(TAG, "No hay permisos, no se puede mostrar ninguna notificación")
            return
        }

        try {
            createNotificationChannelIfNeeded()

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notification = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Tarea nueva")
                .setContentText("Tarea: $taskTitle. No te olvides de poner la fecha límite.")
                .setSmallIcon(R.drawable.ic_notification) // Cambia por el nombre correcto de tu icono
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(0, 250, 250, 250))
                .build()

            notificationManager.notify(idTask.hashCode(), notification)
            android.util.Log.d(TAG, "Se creó correctamente la notificación")

        } catch (e: SecurityException) {
            android.util.Log.e(TAG, "Error de seguridad al crear notificación: ${e.message}")
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error inesperado al crear notificación: ${e.message}")
        }
    }

    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
        }
    }

    private fun createNotificationChannelIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (notificationManager.getNotificationChannel(Constants.NOTIFICATION_CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    Constants.NOTIFICATION_CHANNEL_ID,
                    "Tareas",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Canal para notificaciones de tareas"
                    enableVibration(true)
                    vibrationPattern = longArrayOf(0, 250, 250, 250)

                    val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val audioAttributes = AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                    setSound(soundUri, audioAttributes)
                }

                notificationManager.createNotificationChannel(channel)
                android.util.Log.d(TAG, "Canal de notificación creado")
            }
        }
    }
}