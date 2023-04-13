package com.example.data.network.firebase.remoteconfig

import android.util.Log
import com.example.data.models.AppConfig
import com.example.data.repositories.DataAppConfigCallback
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class FirebaseConfig {

    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    lateinit var appConfig: AppConfig

    /**
     * This method is used to set firebase remote configuration
     */
    init {
        val settings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60)
            .build()

        remoteConfig.setConfigSettingsAsync(settings)
    }

    /**
     * This method is used to get application configuration
     */
    fun fetchFirebaseConfig(dataAppConfigCallback: DataAppConfigCallback) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    appConfig = AppConfig(
                        gamePass = remoteConfig.getBoolean(GAME_PASS_KEY),
                        webLink = remoteConfig.getString(WEB_LINK_KEY)
                    )
                    dataAppConfigCallback.invoke(appConfig)

                    Log.i(
                        GAME_PASS_KEY, "task.result = ${task.result}\n" +
                                "${appConfig.gamePass}\n" +
                                appConfig.webLink
                    )
                } else {
                    Log.e(TAG, "Fetch Failed", task.exception)
                }
            }
    }

    companion object {
        const val TAG = "FirebaseConfig"
        const val GAME_PASS_KEY = "game_pass"
        const val WEB_LINK_KEY = "web_link"
    }
}