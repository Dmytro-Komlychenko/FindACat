package com.example.domain.usecases

import com.example.domain.repositoriesi.DatabaseRepositoryI
import com.example.domain.repositoriesi.DomainGetInventoryCallback

class GetInventoryUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(domainGetInventoryCallback: DomainGetInventoryCallback) {
        databaseRepository.getInventory {
            domainGetInventoryCallback.invoke(it)
        }
    }
}
