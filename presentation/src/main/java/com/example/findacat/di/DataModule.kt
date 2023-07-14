package com.example.findacat.di

import android.content.Context
import com.example.data.repositories.AppActionRepository
import com.example.data.repositories.DatabaseRepository
import com.example.data.repositories.IsFirstLaunchRepository
import com.example.data.storage.room.dao.ProductDao
import com.example.data.storage.room.dao.ResultDao
import com.example.data.storage.sharedprefs.AppActionSharedPrefs
import com.example.data.storage.sharedprefs.GameMoneySharedPrefs
import com.example.data.storage.sharedprefs.IsFirstLaunchSharedPrefs
import com.example.domain.repositoriesi.AppActionRepositoryI
import com.example.domain.repositoriesi.DatabaseRepositoryI
import com.example.domain.repositoriesi.IsFirstLaunchRepositoryI
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideAppActionRepository(appActionSharedPrefs: AppActionSharedPrefs): AppActionRepositoryI {
        return AppActionRepository(appActionSharedPrefs = appActionSharedPrefs)
    }

    @Provides
    fun provideIsFirstLaunchRepository(isFirstLaunchSharedPrefs: IsFirstLaunchSharedPrefs): IsFirstLaunchRepositoryI {
        return IsFirstLaunchRepository(isFirstLaunchSharedPrefs = isFirstLaunchSharedPrefs)
    }

    @Provides
    fun provideDatabaseRepository(
        context: Context,
        gameMoneySharedPrefs: GameMoneySharedPrefs
    ): DatabaseRepositoryI {
        return DatabaseRepository(
            context = context,
            gameMoneySharedPrefs = gameMoneySharedPrefs
        )
    }

    @Provides
    fun provideIsFirstLaunchSharedPrefs(context: Context): IsFirstLaunchSharedPrefs {
        return IsFirstLaunchSharedPrefs(context = context)
    }

    @Provides
    fun provideAppActionSharedPrefs(context: Context): AppActionSharedPrefs {
        return AppActionSharedPrefs(context = context)
    }

    @Provides
    fun provideGameMoneySharedPrefs(context: Context): GameMoneySharedPrefs {
        return GameMoneySharedPrefs(context = context)
    }
}