package com.example.presentation.ui.activities.main.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.LogLinkUseCase

class WebViewViewModelFactory(
    private val logLinkUseCase: LogLinkUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WebViewViewModel(
            logLinkUseCase = logLinkUseCase,
        ) as T
    }
}