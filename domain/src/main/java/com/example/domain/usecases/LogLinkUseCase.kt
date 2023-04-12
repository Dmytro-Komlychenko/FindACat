package com.example.domain.usecases

import com.example.domain.repositoriesi.DatabaseRepositoryI

class LogLinkUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(webLink: String) {
        databaseRepository.logLink(webLink)
    }
}