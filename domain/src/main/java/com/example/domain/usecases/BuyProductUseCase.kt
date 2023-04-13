package com.example.domain.usecases

import com.example.domain.models.Product
import com.example.domain.repositoriesi.DatabaseRepositoryI

class BuyProductUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(product: Product) {
        databaseRepository.buyProduct(product)
    }
}
