package com.example.domain.usecases

import com.example.domain.models.Product
import com.example.domain.repositoriesi.DatabaseRepositoryI

class GetInventoryUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(): List<Product> {
        return databaseRepository.getInventory()
    }
}
