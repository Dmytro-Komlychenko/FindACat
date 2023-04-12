package com.example.presentation.di

import com.example.data.network.firebase.realtime.FirebaseDB
import com.example.data.network.firebase.remoteconfig.FirebaseConfig
import com.example.data.repositories.AppConfigRepository
import com.example.data.repositories.DatabaseRepository
import com.example.domain.repositoriesi.AppConfigRepositoryI
import com.example.domain.repositoriesi.DatabaseRepositoryI
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideAppConfigRepository(firebaseConfig: FirebaseConfig): AppConfigRepositoryI {
        return AppConfigRepository(firebaseConfig = firebaseConfig)
    }

    @Provides
    fun provideDatabaseRepository(firebaseDB: FirebaseDB): DatabaseRepositoryI {
        return DatabaseRepository(firebaseDB = firebaseDB)
    }
    @Provides
    fun provideFirebaseConfig(): FirebaseConfig {
        return FirebaseConfig()
    }

    @Provides
    fun provideFirebaseDB(): FirebaseDB {
        return FirebaseDB()
    }
}