package com.example.tasks.src.core.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplicationmovil.src.features.login.presentation.view.LoginScreen
import com.example.myapplicationmovil.src.features.notes.presentation.view.HomeScreen
import com.example.myapplicationmovil.src.features.register.presentation.view.RegisterScreen
import com.example.yourapp.ui.screens.EditNoteScreen
import com.example.yourapp.ui.screens.NewNoteScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Login") {

        // Login screen
        composable("Login") {
            LoginScreen(
                onLoginSuccess = { userId ->
                    navController.navigate("Home/$userId")
                },
                onRegisterClick = {
                    navController.navigate("Register")
                }
            )
        }

        // Register screen
        composable("Register") {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate("Login")
                },
                onRegisterSuccess = { userId ->
                    navController.navigate("Home/$userId")
                }
            )
        }

        // Home screen
        composable(
            route = "Home/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            HomeScreen(
                userId = userId,
                onCreateClick = {
                    navController.navigate("Create/$userId")
                },
                onEditClick = { noteId ->
                    navController.navigate("Edit/$userId/$noteId")
                }
            )
        }

        // New note screen
        composable(
            route = "Create/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            NewNoteScreen(
                userId = userId,
                onNavigateBack = {
                    navController.navigate("Home/$userId") {
                        popUpTo("Home/$userId") { inclusive = true }
                    }
                }
            )
        }

        // Edit note screen
        composable(
            route = "Edit/{userId}/{noteId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("noteId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
            EditNoteScreen(
                userId = userId,
                noteId = noteId,
                onNavigateBack = {
                    navController.navigate("Home/$userId") {
                        popUpTo("Home/$userId") { inclusive = true }
                    }
                }
            )
        }
    }
}
