package com.example.myapplicationmovil.src.features.notes.presentation.view
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplicationmovil.src.features.notes.data.di.AppModule
import com.example.myapplicationmovil.src.features.notes.presentation.viewModel.DeleteTaskViewModel
import com.example.myapplicationmovil.src.features.notes.presentation.viewModel.DeleteTaskViewModelFactory
import com.example.myapplicationmovil.src.features.notes.presentation.viewModel.GetAllTaskViewModel
import com.example.myapplicationmovil.src.features.notes.presentation.viewModel.GetAllTaskViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userId: String,
    onCreateClick: () -> Unit,
    onEditClick: (String) -> Unit
) {

    val getTasksViewModel: GetAllTaskViewModel = viewModel(
        factory = GetAllTaskViewModelFactory(AppModule.getAllTaskUseCase)
    )

    val deleteTaskViewModel: DeleteTaskViewModel = viewModel(
        factory = DeleteTaskViewModelFactory(AppModule.deleteTaskUseCase)
    )



    val tasks by getTasksViewModel.tasks.collectAsState()
    val errorMessage by getTasksViewModel.errorMessage.collectAsState()
    val isLoading by getTasksViewModel.isLoading.collectAsState()

    LaunchedEffect(userId) {
        getTasksViewModel.fetchTasksForUser(userId)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Notes 1.1") },
                actions = {
                    IconButton(onClick = {onCreateClick()}) {
                        Icon(Icons.Default.Add, contentDescription = "Add Note")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {

            items(tasks) { task ->
                NoteCard(task, onEdit = { onEditClick(task.id) },
                    onDelete = {
                    deleteTaskViewModel.deleteTask(task.id)
                     getTasksViewModel.removeTaskById(task.id)
                })
            }
        }
    }
}
