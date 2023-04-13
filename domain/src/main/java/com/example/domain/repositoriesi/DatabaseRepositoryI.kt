package com.example.domain.repositoriesi

import com.example.domain.models.Product
import com.example.domain.models.Result

typealias DomainGetResultsCallback = (ArrayList<Result>) -> Unit
typealias DomainGetInventoryCallback = (ArrayList<Product>) -> Unit

interface DatabaseRepositoryI {
    suspend fun logLink(webLink: String)
    suspend fun updateMoney(money: Float)
    suspend fun saveResult(result: Result)
    suspend fun buyProduct(product: Product)
    suspend fun getResults(domainGetResultsCallback: DomainGetResultsCallback)
    suspend fun getInventory(domainGetInventoryCallback: DomainGetInventoryCallback)

}