package com.example.domain.repositoriesi

import com.example.domain.models.IsFirstLaunch

typealias DomainIsFirstLaunchCallback = (IsFirstLaunch) -> Unit

interface IsFirstLaunchRepositoryI {
    suspend fun getIsFirstLaunch(domainIsFirstLaunchCallback: DomainIsFirstLaunchCallback)
    suspend fun launch()
}