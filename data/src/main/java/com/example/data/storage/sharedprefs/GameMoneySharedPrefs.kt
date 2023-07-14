package com.example.data.storage.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.utils.Constants

class GameMoneySharedPrefs(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            Constants.SHARED_PREFS_GAME_MONEY_KEY,
            Context.MODE_PRIVATE
        )

    fun update(value: Float) {
        sharedPreferences.edit().putFloat(Constants.GAME_MONEY, value).apply()
    }

    fun get(): Float {
        return sharedPreferences.getFloat(Constants.GAME_MONEY, 0F)
    }

}