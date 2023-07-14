package com.example.data.repositories

import com.example.data.storage.sharedprefs.IsFirstLaunchSharedPrefs
import com.example.domain.models.IsFirstLaunch
import com.example.domain.repositoriesi.DomainIsFirstLaunchCallback
import com.example.domain.repositoriesi.IsFirstLaunchRepositoryI

class IsFirstLaunchRepository(
    var isFirstLaunchSharedPrefs: IsFirstLaunchSharedPrefs
) : IsFirstLaunchRepositoryI {
    override suspend fun getIsFirstLaunch(domainIsFirstLaunchCallback: DomainIsFirstLaunchCallback) {
        domainIsFirstLaunchCallback.invoke(
            IsFirstLaunch(isFirstLaunchSharedPrefs.get())
        )
    }

    override suspend fun launch() {
        isFirstLaunchSharedPrefs.launch()
    }
}