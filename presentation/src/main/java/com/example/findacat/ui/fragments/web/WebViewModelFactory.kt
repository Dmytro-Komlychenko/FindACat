package com.example.findacat.ui.fragments.web

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WebViewModelFactory
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WebViewModel() as T
    }
}