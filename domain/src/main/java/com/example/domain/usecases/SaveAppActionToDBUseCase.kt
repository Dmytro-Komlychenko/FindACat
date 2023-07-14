package com.example.domain.usecases

import com.example.domain.models.AppAction
import com.example.domain.repositoriesi.AppActionRepositoryI

class SaveAppActionToDBUseCase(private val appActionRepository: AppActionRepositoryI) {
    suspend fun execute(appAction: AppAction) {
        appActionRepository.saveAppActionToDB(appAction)
    }
}