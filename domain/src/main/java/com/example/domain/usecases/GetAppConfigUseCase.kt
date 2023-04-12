package com.example.domain.usecases

import com.example.domain.repositoriesi.AppConfigRepositoryI
import com.example.domain.repositoriesi.DomainAppConfigCallback

class GetAppConfigUseCase(private val appConfigRepository: AppConfigRepositoryI) {
    suspend fun execute(domainAppConfigCallback: DomainAppConfigCallback) {
        appConfigRepository.getConfig {
            domainAppConfigCallback(it)
        }
    }
}