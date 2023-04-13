package com.example.data.repositories

import com.example.data.models.Result
import com.example.data.network.firebase.realtime.FirebaseDB
import com.example.data.models.Product
import com.example.domain.repositoriesi.DatabaseRepositoryI
import com.example.domain.repositoriesi.DomainGetInventoryCallback
import com.example.domain.repositoriesi.DomainGetResultsCallback

typealias DataGetResultsCallback = (ArrayList<Result>) -> Unit
typealias DataGetInventoryCallback = (ArrayList<Product>) -> Unit

class DatabaseRepository(val firebaseDB: FirebaseDB) : DatabaseRepositoryI {

    override suspend fun logLink(webLink: String) {
        firebaseDB.logLink(webLink)
    }

    override suspend fun saveResult(result: com.example.domain.models.Result) {
        firebaseDB.saveResult(Result.mapDomainToData(result))
    }

    override suspend fun getResults(domainGetResultsCallback: DomainGetResultsCallback) {
        firebaseDB.getResults {
            val domainArrayList = arrayListOf<com.example.domain.models.Result>()
            it.forEach { result ->
                domainArrayList.add(result.mapDataToDomain())
            }
            domainGetResultsCallback.invoke(domainArrayList)
        }
    }

    override suspend fun buyProduct(product: com.example.domain.models.Product) {
        val dataProduct = Product.mapDomainToData(product)
        firebaseDB.buyProduct(dataProduct)
    }

    override suspend fun getInventory(domainGetInventoryCallback: DomainGetInventoryCallback) {
        firebaseDB.getInventory {
            val domainInventory = arrayListOf<com.example.domain.models.Product>()
            it.forEach {
                domainInventory.add(it.mapDataToDomain())
            }
            domainGetInventoryCallback.invoke(domainInventory)
        }
    }
}