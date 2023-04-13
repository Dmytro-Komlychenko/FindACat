package com.example.presentation.ui.fragments.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.*
import com.example.presentation.models.Product
import com.example.presentation.models.Result
import kotlinx.coroutines.launch

class GameViewModel(
    private val saveResultUseCase: SaveResultUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val buyProductUseCase: BuyProductUseCase,
    private val getInventoryUseCase: GetInventoryUseCase,
    private val updateMoneyUseCase: UpdateMoneyUseCase,
) : ViewModel() {

    var counterCatsFound = 0
    val inventory: MutableLiveData<ArrayList<Product>> = MutableLiveData()
    val results: MutableLiveData<ArrayList<Result>> = MutableLiveData()
    var money: Float = 0F

    init {
        getResults()
        getInventory()
    }

    /**
     * This method is used to save game's result into game history
     */
    fun saveResult() {
        viewModelScope.launch {
            val tryNumber: Int =
                if (results.value?.isNotEmpty() == true) {
                    results.value?.maxOf { res -> res.tryNumber }!!.plus(1)
                } else 1

            saveResultUseCase.execute(
                com.example.domain.models.Result(
                    tryNumber,
                    counterCatsFound
                )
            )
        }
    }

    /**
     * This method is used to get game history from storage
     */
    private fun getResults() {
        viewModelScope.launch {
            getResultsUseCase.execute {
                val value = arrayListOf<Result>()
                it.forEach { result ->
                    value.add(Result.mapDomainToPresentation(result))
                }
                results.value = value
            }
        }
    }

    /**
     * This method is used to get bought items
     */
    private fun getInventory() {
        viewModelScope.launch {
            getInventoryUseCase.execute {
                val value = arrayListOf<Product>()
                it.forEach {
                    value.add(Product.mapDomainToPresentation(it))
                }
                inventory.value = value
            }
        }
    }

    /**
     * This method is used to buy a product
     */
    fun buyProduct(product: Product) {
        viewModelScope.launch {
            buyProductUseCase.execute(product.mapPresentationToDomain())
            inventory.value?.add(product)
        }
    }

    /**
     * This method is used to set money count
     */
    fun updateMoney() {
        viewModelScope.launch {
            updateMoneyUseCase.execute(money)
        }
    }
}