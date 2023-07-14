package com.example.domain.usecases

import com.example.domain.repositoriesi.DomainIsFirstLaunchCallback
import com.example.domain.repositoriesi.IsFirstLaunchRepositoryI

class GetIsFirstLaunchUseCase(private val isFirstLaunchRepository: IsFirstLaunchRepositoryI) {
    suspend fun execute(domainIsFirstLaunchCallback: DomainIsFirstLaunchCallback) {
        isFirstLaunchRepository.getIsFirstLaunch {
            domainIsFirstLaunchCallback(it)
        }
    }
}