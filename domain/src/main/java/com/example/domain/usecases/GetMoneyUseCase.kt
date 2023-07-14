package com.example.domain.usecases

import com.example.domain.repositoriesi.DatabaseRepositoryI

class GetMoneyUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(): Float {
        return databaseRepository.getMoney()

    }
}