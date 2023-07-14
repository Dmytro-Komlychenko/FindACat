package com.example.findacat.ui.fragments.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.*

class GameViewModelFactory(
    private val saveResultUseCase: SaveResultUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val buyProductUseCase: BuyProductUseCase,
    private val getInventoryUseCase: GetInventoryUseCase,
    private val updateMoneyUseCase: UpdateMoneyUseCase,
    private val getMoneyUseCase: GetMoneyUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(
            saveResultUseCase = saveResultUseCase,
            getResultsUseCase = getResultsUseCase,
            buyProductUseCase = buyProductUseCase,
            getInventoryUseCase = getInventoryUseCase,
            updateMoneyUseCase = updateMoneyUseCase,
            getMoneyUseCase = getMoneyUseCase,
        ) as T
    }
}
