package com.example.domain.usecases

import com.example.domain.repositoriesi.DatabaseRepositoryI
import com.example.domain.repositoriesi.DomainGetResultsCallback

class GetResultsUseCase(val databaseRepository: DatabaseRepositoryI) {
    suspend fun execute(domainGetResultsCallback: DomainGetResultsCallback) {
        databaseRepository.getResults {
            domainGetResultsCallback.invoke(it)
        }
    }
}
