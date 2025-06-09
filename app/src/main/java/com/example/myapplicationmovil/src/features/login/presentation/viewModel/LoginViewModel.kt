package com.example.myapplicationmovil.src.features.login.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationmovil.src.features.login.domain.models.AuthRequest
import com.example.myapplicationmovil.src.features.login.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel (
    private val authUseCase: AuthUseCase
): ViewModel(){

    private val _idUser = MutableStateFlow<String>("")
    val idUser: StateFlow<String> = _idUser

    private var _email = MutableStateFlow<String>("")
    val email : StateFlow<String> = _email

    private var _password = MutableStateFlow<String>("")
    val password : StateFlow<String> = _password

    private val _message = MutableStateFlow<String>("")
    val message: StateFlow<String> = _message

    private val _success =MutableStateFlow<Boolean>(false)
    val success: StateFlow<Boolean> = _success


    fun onChangeEmail(email: String){
        _email.value = email
    }

    fun onChangePassword (password: String) {
        _password.value = password
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private  fun isPasswordValid(password: String): Boolean {
        return password.length >= 5
    }

    fun validateFields(): Boolean {
        val currentEmail = _email.value
        val currentPassword = _password.value

        return when {
            currentEmail.isBlank() -> {
                _message.value = "Email cannot be empty"
                _success.value = false
                false
            }
            currentPassword.isBlank() -> {
                _message.value = "Password cannot be empty"
                _success.value = false
                false
            }
            !isEmailValid(currentEmail) -> {
                _message.value = "Please enter a valid email address"
                _success.value = false
                false
            }
            !isPasswordValid(currentPassword) -> {
                _message.value = "The password must be at least 4 characters long"
                _success.value = false
                false
            }
            else -> {
                _message.value = ""
                true
            }
        }
    }

    fun onClick() {
        if (!validateFields()) return

        val user = AuthRequest( email = _email.value, password = _password.value)


        viewModelScope.launch {
            try {
                val result = authUseCase(user)

                _idUser.value = result.user.id
                _message.value = "Inicio de sesión exitoso"
                _success.value = true


            } catch (e: Exception) {
                _message.value = e.message ?: "Unknown error"
                _success.value = false
            }
        }
    }


}