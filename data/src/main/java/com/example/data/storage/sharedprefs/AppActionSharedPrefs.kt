package com.example.data.storage.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.utils.Constants

class AppActionSharedPrefs(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            Constants.SHARED_PREFS_APP_ACTION_KEY,
            Context.MODE_PRIVATE
        )

    fun save(value: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.APP_ACTION, value).apply()
    }

    fun get(): Boolean {
        return sharedPreferences.getBoolean(Constants.APP_ACTION, false)
    }
}