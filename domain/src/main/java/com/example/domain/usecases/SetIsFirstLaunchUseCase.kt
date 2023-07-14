package com.example.domain.usecases

import com.example.domain.repositoriesi.IsFirstLaunchRepositoryI

class SetIsFirstLaunchUseCase(private val isFirstLaunchRepository: IsFirstLaunchRepositoryI) {
    suspend fun execute() {
        isFirstLaunchRepository.launch()
    }
}