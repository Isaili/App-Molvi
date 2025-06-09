package com.example.yourapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplicationmovil.src.features.notes.data.di.AppModule
import com.example.myapplicationmovil.src.features.notes.presentation.viewModel.UpdateTaskVIewModelFactory
import com.example.myapplicationmovil.src.features.notes.presentation.viewModel.UpdateTaskViewModel
import kotlinx.coroutines.launch

@Composable
fun EditNoteScreen(
    userId: String,
    noteId: String,
    onNavigateBack: () -> Unit
) {

    val viewModel: UpdateTaskViewModel = viewModel(
        factory = UpdateTaskVIewModelFactory(AppModule.updateTaskUseCase)
    )

    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val message by viewModel.message.collectAsState()
    val success by viewModel.success.collectAsState()

    // Navegar de vuelta cuando se actualice exitosamente la nota
    LaunchedEffect(success) {
        if (success) {
            kotlinx.coroutines.delay(2000) // Esperar 2 segundos para que el usuario vea el mensaje
            onNavigateBack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Edit Note",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = "+",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Mostrar mensaje de estado
        if (message.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (success) Color(0xFF4CAF50) else Color(0xFFFF5722)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Título
        TextField(
            value = title,
            onValueChange = { viewModel.onTitleChanged(it) },
            placeholder = { Text("Hola, esta es una nota editable", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(1.dp, RoundedCornerShape(12.dp))
                .border(1.2.dp, Color.LightGray, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Descripción
        TextField(
            value = description,
            onValueChange = { viewModel.onDescriptionChanged(it) },
            placeholder = { Text("El contenido de tu nota se visualizará aquí.", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .shadow(1.dp, RoundedCornerShape(12.dp))
                .border(1.2.dp, Color.LightGray, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botón Guardar
        Button(
            onClick = {

                    viewModel.updateTask(idTask = noteId )

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text("Guardar", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}