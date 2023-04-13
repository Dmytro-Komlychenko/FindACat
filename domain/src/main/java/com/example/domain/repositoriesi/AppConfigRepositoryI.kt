package com.example.domain.repositoriesi

import com.example.domain.models.AppConfig

typealias DomainAppConfigCallback = (AppConfig) -> Unit
interface AppConfigRepositoryI {
    suspend fun getConfig(domainAppConfigCallback: DomainAppConfigCallback)
}