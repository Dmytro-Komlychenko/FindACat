package com.example.presentation.ui.activities.main.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.LogLinkUseCase
import kotlinx.coroutines.launch

class WebViewViewModel(
    private val logLinkUseCase: LogLinkUseCase
) : ViewModel() {
    val onBackPressed: MutableLiveData<Boolean> = MutableLiveData(false)

    fun logLink(webLink: String) {
        viewModelScope.launch {
            logLinkUseCase.execute(webLink)
        }
    }
}