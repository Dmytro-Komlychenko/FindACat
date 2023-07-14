package com.example.findacat.di

import com.example.domain.repositoriesi.AppActionRepositoryI
import com.example.domain.repositoriesi.DatabaseRepositoryI
import com.example.domain.repositoriesi.IsFirstLaunchRepositoryI
import com.example.domain.usecases.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetAppActionFromDBUseCase(appActionRepositoryI: AppActionRepositoryI): GetAppActionFromDBUseCase {
        return GetAppActionFromDBUseCase(appActionRepository = appActionRepositoryI)
    }

    @Provides
    fun provideGetAppActionFromServerUseCase(appActionRepositoryI: AppActionRepositoryI): GetAppActionFromServerUseCase {
        return GetAppActionFromServerUseCase(appActionRepository = appActionRepositoryI)
    }

    @Provides
    fun provideGetIsFirstLaunchUseCase(isFirstLaunchRepositoryI: IsFirstLaunchRepositoryI): GetIsFirstLaunchUseCase {
        return GetIsFirstLaunchUseCase(isFirstLaunchRepository = isFirstLaunchRepositoryI)
    }

    @Provides
    fun provideSetIsFirstLaunchUseCase(isFirstLaunchRepositoryI: IsFirstLaunchRepositoryI): SetIsFirstLaunchUseCase {
        return SetIsFirstLaunchUseCase(isFirstLaunchRepository = isFirstLaunchRepositoryI)
    }

    @Provides
    fun provideSaveAppActionToDBUseCase(appActionRepositoryI: AppActionRepositoryI): SaveAppActionToDBUseCase {
        return SaveAppActionToDBUseCase(appActionRepository = appActionRepositoryI)
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
    fun provideGetResultsUseCase(databaseRepositoryI: DatabaseRepositoryI): GetResultsUseCase {
        return GetResultsUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideGetMoneyUseCase(databaseRepositoryI: DatabaseRepositoryI): GetMoneyUseCase {
        return GetMoneyUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideSaveResultUseCase(databaseRepositoryI: DatabaseRepositoryI): SaveResultUseCase {
        return SaveResultUseCase(databaseRepository = databaseRepositoryI)
    }

    @Provides
    fun provideUpdateMoneyUseCase(databaseRepositoryI: DatabaseRepositoryI): UpdateMoneyUseCase {
        return UpdateMoneyUseCase(databaseRepository = databaseRepositoryI)
    }
}