package com.example.presentation.ui.activities.main.config

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetAppConfigUseCase
import com.example.presentation.models.AppConfig
import kotlinx.coroutines.launch

class ConfigViewModel(
    private val getAppConfigUseCase: GetAppConfigUseCase,
) : ViewModel() {

    val appConfig: MutableLiveData<AppConfig> = MutableLiveData()

    init {
        viewModelScope.launch {
            getAppConfigUseCase.execute {
                appConfig.value = AppConfig.mapDomainToPresentation(it)
            }
        }
    }
}