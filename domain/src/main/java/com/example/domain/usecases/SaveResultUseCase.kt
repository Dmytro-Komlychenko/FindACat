package com.example.domain.usecases

import com.example.domain.repositoriesi.DatabaseRepositoryI

class SaveResultUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(result: com.example.domain.models.Result) {
        databaseRepository.saveResult(result)
    }
}