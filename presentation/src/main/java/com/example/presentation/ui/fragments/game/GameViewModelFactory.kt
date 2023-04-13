package com.example.presentation.ui.fragments.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.BuyProductUseCase
import com.example.domain.usecases.GetInventoryUseCase
import com.example.domain.usecases.GetResultsUseCase
import com.example.domain.usecases.SaveResultUseCase

class GameViewModelFactory(
    private val saveResultUseCase: SaveResultUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val buyProductUseCase: BuyProductUseCase,
    private val getInventoryUseCase: GetInventoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(
            saveResultUseCase = saveResultUseCase,
            getResultsUseCase = getResultsUseCase,
            buyProductUseCase = buyProductUseCase,
            getInventoryUseCase = getInventoryUseCase,
        ) as T
    }
}
