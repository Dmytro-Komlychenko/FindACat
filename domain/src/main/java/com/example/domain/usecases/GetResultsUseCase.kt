package com.example.domain.usecases

import com.example.domain.repositoriesi.DatabaseRepositoryI

class GetResultsUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(): List<com.example.domain.models.Result> {
        return databaseRepository.getResults()
    }
}