package com.example.domain.usecases

import com.example.domain.repositoriesi.AppActionRepositoryI
import com.example.domain.repositoriesi.DomainAppActionCallback

class GetAppActionFromServerUseCase(private val appActionRepository: AppActionRepositoryI) {
    suspend fun execute(domainAppActionCallback: DomainAppActionCallback) {
        appActionRepository.getAppActionFromServer {
            domainAppActionCallback(it)
        }
    }
}
