package com.example.presentation.ui.fragments.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetResultsUseCase
import com.example.domain.usecases.SaveResultUseCase

class GameViewModelFactory(
    private val saveResultUseCase: SaveResultUseCase,
    private val getResultsUseCase: GetResultsUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(
            saveResultUseCase = saveResultUseCase,
            getResultsUseCase = getResultsUseCase,
        ) as T
    }
}
