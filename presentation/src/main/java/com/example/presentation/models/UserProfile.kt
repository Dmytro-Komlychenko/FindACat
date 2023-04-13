package com.example.presentation.models

import androidx.lifecycle.MutableLiveData

data class UserProfile(
    val inventory: MutableLiveData<ArrayList<Product>> = MutableLiveData(),
    val results: MutableLiveData<ArrayList<Result>> = MutableLiveData(),
    var money: Float = 0F
)