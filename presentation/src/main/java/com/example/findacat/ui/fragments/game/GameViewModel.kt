package com.example.findacat.ui.fragments.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.*
import com.example.findacat.models.Product
import com.example.findacat.models.Result
import kotlinx.coroutines.launch

class GameViewModel(
    private val saveResultUseCase: SaveResultUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val buyProductUseCase: BuyProductUseCase,
    private val getInventoryUseCase: GetInventoryUseCase,
    private val updateMoneyUseCase: UpdateMoneyUseCase,
    private val getMoneyUseCase: GetMoneyUseCase,
) : ViewModel() {

    var counterCatsFound = 0
    val inventory: MutableLiveData<ArrayList<Product>> = MutableLiveData()
    val results: MutableLiveData<ArrayList<Result>> = MutableLiveData()
    var money: Float = 0F

    init {
        getResults()
        getInventory()
        initMoney()
    }

    fun saveResult() {
        viewModelScope.launch {
            val tryNumber: Int =
                if (results.value?.isNotEmpty() == true) {
                    results.value?.maxOf { res -> res.tryNumber }!!.plus(1)
                } else 1

            val res = com.example.domain.models.Result(
                tryNumber,
                counterCatsFound
            )
            counterCatsFound = 0
            saveResultUseCase.execute(res)
            results.value?.add(Result.mapDomainToPresentation(res))
        }
    }

    private fun getResults() {
        viewModelScope.launch {
            val tempResults = arrayListOf<Result>()
            getResultsUseCase.execute()
                .forEach { tempResults.add(Result.mapDomainToPresentation(it)) }
            results.value = tempResults
        }
    }

    private fun getInventory() {
        viewModelScope.launch {
            val tempInventory = arrayListOf<Product>()
            getInventoryUseCase.execute()
                .forEach { tempInventory.add(Product.mapDomainToPresentation(it)) }
            inventory.value = tempInventory
        }
    }

    private fun initMoney() {
        viewModelScope.launch {
            money = getMoneyUseCase.execute()
        }
    }

    fun buyProduct(product: Product) {
        viewModelScope.launch {
            buyProductUseCase.execute(product.mapPresentationToDomain())
            inventory.value?.add(product)
        }
    }

    fun updateMoney() {
        viewModelScope.launch {
            updateMoneyUseCase.execute(money)
        }
    }
}