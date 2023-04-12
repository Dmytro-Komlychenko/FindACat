package com.example.presentation.ui.fragments.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetResultsUseCase
import com.example.domain.usecases.SaveResultUseCase
import com.example.presentation.models.Result
import kotlinx.coroutines.launch

class GameViewModel(
    private val saveResultUseCase: SaveResultUseCase,
    private val getResultsUseCase: GetResultsUseCase,
) : ViewModel() {

    var counterCatsFound = 0
    val results: MutableLiveData<ArrayList<Result>> =
        MutableLiveData()

    init {
        getResults()
    }

    fun saveResult() {
        viewModelScope.launch {
            saveResultUseCase.execute(com.example.domain.models.Result(0, counterCatsFound))
        }
    }

    private fun getResults() {
        viewModelScope.launch {
            getResultsUseCase.execute {
                val presentationResults = arrayListOf<Result>()
                it.forEach { result ->
                    presentationResults.add(Result.mapDomainToPresentation(result))
                }
                results.value = presentationResults
            }
        }
    }
}