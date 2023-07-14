package com.example.domain.repositoriesi

import com.example.domain.models.AppAction

typealias DomainAppActionCallback = (AppAction) -> Unit

interface AppActionRepositoryI {
    suspend fun getAppActionFromServer(domainAppActionCallback: DomainAppActionCallback)
    suspend fun getAppActionFromDB(domainAppActionCallback: DomainAppActionCallback)
    suspend fun saveAppActionToDB(value: AppAction)
}