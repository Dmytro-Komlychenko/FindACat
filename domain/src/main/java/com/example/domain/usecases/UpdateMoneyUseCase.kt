package com.example.domain.usecases

import com.example.domain.repositoriesi.DatabaseRepositoryI

class UpdateMoneyUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(money: Float) {
        databaseRepository.updateMoney(money)
    }
}