package com.example.data.repositories

import com.example.data.network.firebase.remoteconfig.FirebaseConfig
import com.example.domain.repositoriesi.AppConfigRepositoryI
import com.example.domain.repositoriesi.DomainAppConfigCallback

typealias DataAppConfigCallback = (com.example.data.models.AppConfig) -> Unit

class AppConfigRepository(val firebaseConfig: FirebaseConfig) : AppConfigRepositoryI {

    override suspend fun getConfig(domainAppConfigCallback: DomainAppConfigCallback) {
        firebaseConfig.fetchFirebaseConfig {
            domainAppConfigCallback.invoke(it.mapDataToDomain())
        }
    }
}