package com.example.data.repositories

import com.example.data.network.RetrofitInstance
import com.example.data.storage.sharedprefs.AppActionSharedPrefs
import com.example.domain.models.AppAction
import com.example.domain.models.AppActionE
import com.example.domain.repositoriesi.AppActionRepositoryI
import com.example.domain.repositoriesi.DomainAppActionCallback

class AppActionRepository(
    var appActionSharedPrefs: AppActionSharedPrefs
) : AppActionRepositoryI {

    override suspend fun getAppActionFromServer(domainAppActionCallback: DomainAppActionCallback) {
        val appAction = when (RetrofitInstance.api.getInt() == 1) {
            false -> {
                AppActionE.GAME
            }
            true -> {
                AppActionE.WEB
            }
        }
        domainAppActionCallback.invoke(AppAction(appAction))
    }

    override suspend fun getAppActionFromDB(domainAppActionCallback: DomainAppActionCallback) {
        val appAction = when (appActionSharedPrefs.get()) {
            false -> {
                AppActionE.GAME
            }
            true -> {
                AppActionE.WEB
            }
        }
        domainAppActionCallback.invoke(AppAction(appAction))
    }

    override suspend fun saveAppActionToDB(value: AppAction) {
        val appAction = when (value.value) {
            AppActionE.GAME -> {
                false
            }
            AppActionE.WEB -> {
                true
            }
        }
        appActionSharedPrefs.save(appAction)
    }
}