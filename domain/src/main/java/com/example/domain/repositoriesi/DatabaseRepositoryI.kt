package com.example.domain.repositoriesi

import com.example.domain.models.Product
import com.example.domain.models.Result

interface DatabaseRepositoryI {
    suspend fun updateMoney(money: Float)
    suspend fun getMoney(): Float
    suspend fun saveResult(result: Result)
    suspend fun buyProduct(product: Product)
    suspend fun getResults() : List<Result>
    suspend fun getInventory(): List<Product>
}