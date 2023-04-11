package com.example.testgame.ui.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewViewModel: ViewModel() {
    val onBackPressed: MutableLiveData<Boolean> = MutableLiveData(false)
}