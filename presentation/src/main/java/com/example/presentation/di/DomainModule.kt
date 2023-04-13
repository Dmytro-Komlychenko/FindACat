package com.example.presentation.di

import com.example.domain.repositoriesi.AppConfigRepositoryI
import com.example.domain.repositoriesi.DatabaseRepositoryI
import com.example.domain.usecases.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetAppConfigUseCase(appConfigRepository: AppConfigRepositoryI): GetAppConfigUseCase{
        return GetAppConfigUseCase(appConfigRepository = appConfigRepository)
    }

    @Provides
    fun provideLogLinkUseCase(databaseRepositoryI: DatabaseRepositoryI): LogLinkUseCase {
        return LogLinkUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideSaveResultUseCase(databaseRepositoryI: DatabaseRepositoryI): SaveResultUseCase {
        return SaveResultUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideGetResultsUseCase(databaseRepositoryI: DatabaseRepositoryI): GetResultsUseCase {
        return GetResultsUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideBuyProductUseCase(databaseRepositoryI: DatabaseRepositoryI): BuyProductUseCase {
        return BuyProductUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideGetInventoryUseCase(databaseRepositoryI: DatabaseRepositoryI): GetInventoryUseCase {
        return GetInventoryUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideUpdateMoneyUseCase(databaseRepositoryI: DatabaseRepositoryI): UpdateMoneyUseCase {
        return UpdateMoneyUseCase(databaseRepository = databaseRepositoryI)
    }
}