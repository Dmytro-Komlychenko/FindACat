package com.example.findacat.ui.fragments.web

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewModel : ViewModel() {
    val onBackPressed: MutableLiveData<Boolean> = MutableLiveData(false)

}