package com.example.data.repositories

import android.content.Context
import com.example.data.storage.room.dao.ProductDao
import com.example.data.storage.room.dao.ResultDao
import com.example.data.storage.room.database.FindACatDB
import com.example.data.storage.room.entities.Product
import com.example.data.storage.room.entities.Result
import com.example.data.storage.sharedprefs.GameMoneySharedPrefs
import com.example.domain.repositoriesi.DatabaseRepositoryI

class DatabaseRepository(
    private val context: Context,
    var gameMoneySharedPrefs: GameMoneySharedPrefs
) : DatabaseRepositoryI {

    private var productDao: ProductDao =
        FindACatDB.getDB(context).productDao()

    private var resultDao: ResultDao =
        FindACatDB.getDB(context).resultDao()


    override suspend fun saveResult(result: com.example.domain.models.Result) {
        resultDao.saveResult(Result.mapModelToEntity(result))
    }

    override suspend fun getResults(): List<com.example.domain.models.Result> {
        val results = arrayListOf<com.example.domain.models.Result>()
        resultDao.readAllData().forEach { results.add(Result.mapEntityToModel(it)) }
        return results
    }

    override suspend fun buyProduct(product: com.example.domain.models.Product) {
        productDao.buyProduct(Product.mapModelToEntity(product))
    }

    override suspend fun getInventory(): List<com.example.domain.models.Product> {
        val products = arrayListOf<com.example.domain.models.Product>()
        productDao.getInventory().forEach { products.add(Product.mapEntityToModel(it)) }
        return products
    }

    override suspend fun updateMoney(money: Float) {
        gameMoneySharedPrefs.update(money)
    }

    override suspend fun getMoney(): Float {
        return gameMoneySharedPrefs.get()
    }
}