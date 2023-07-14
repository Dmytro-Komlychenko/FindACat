package com.example.findacat.di

import android.content.Context
import com.example.domain.usecases.*
import com.example.findacat.ui.fragments.game.GameViewModelFactory
import com.example.findacat.ui.fragments.splash.SplashViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideSplashViewModelFactory(
        getIsFirstLaunchUseCase: GetIsFirstLaunchUseCase,
        getAppActionFromServerUseCase: GetAppActionFromServerUseCase,
        getAppActionFromDBUseCase: GetAppActionFromDBUseCase,
        setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase,
        saveAppActionToDBUseCase: SaveAppActionToDBUseCase
    ): SplashViewModelFactory {
        return SplashViewModelFactory(
            getIsFirstLaunchUseCase = getIsFirstLaunchUseCase,
            getAppActionFromServerUseCase = getAppActionFromServerUseCase,
            getAppActionFromDBUseCase = getAppActionFromDBUseCase,
            setIsFirstLaunchUseCase = setIsFirstLaunchUseCase,
            saveAppActionToDBUseCase = saveAppActionToDBUseCase,
        )
    }

    @Provides
    fun provideGameViewModelFactory(
        saveResultUseCase: SaveResultUseCase,
        getResultsUseCase: GetResultsUseCase,
        buyProductUseCase: BuyProductUseCase,
        getInventoryUseCase: GetInventoryUseCase,
        updateMoneyUseCase: UpdateMoneyUseCase,
        getMoneyUseCase: GetMoneyUseCase,
    ): GameViewModelFactory {
        return GameViewModelFactory(
            saveResultUseCase = saveResultUseCase,
            getResultsUseCase = getResultsUseCase,
            buyProductUseCase = buyProductUseCase,
            getInventoryUseCase = getInventoryUseCase,
            updateMoneyUseCase = updateMoneyUseCase,
            getMoneyUseCase = getMoneyUseCase,
        )
    }
}