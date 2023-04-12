package com.example.presentation.ui.activities.main.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetAppConfigUseCase

class ConfigViewModelFactory(
    private val getAppConfigUseCase: GetAppConfigUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConfigViewModel(
            getAppConfigUseCase = getAppConfigUseCase,
        ) as T
    }
}