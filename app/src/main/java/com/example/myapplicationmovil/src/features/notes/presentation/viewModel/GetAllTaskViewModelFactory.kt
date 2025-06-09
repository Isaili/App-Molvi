package com.example.myapplicationmovil.src.features.notes.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationmovil.src.features.notes.domain.usecase.ListTaskUseCase


class GetAllTaskViewModelFactory (
    private val getAllUseCase: ListTaskUseCase
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetAllTaskViewModel::class.java)){
            return GetAllTaskViewModel(getAllUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}