package com.example.presentation.di

import com.example.domain.usecases.*
import com.example.presentation.ui.activities.main.config.ConfigViewModelFactory
import com.example.presentation.ui.activities.main.database.WebViewViewModelFactory
import com.example.presentation.ui.fragments.game.GameViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideConfigViewModelFactory(
        getAppConfigUseCase: GetAppConfigUseCase,
    ): ConfigViewModelFactory {
        return ConfigViewModelFactory(
            getAppConfigUseCase = getAppConfigUseCase
        )
    }

    @Provides
    fun provideWebViewModelFactory(
        logLinkUseCase: LogLinkUseCase,
    ): WebViewViewModelFactory {
        return WebViewViewModelFactory(
            logLinkUseCase = logLinkUseCase
        )
    }

    @Provides
    fun provideGameViewModelFactory(
        saveResultUseCase: SaveResultUseCase,
        getResultsUseCase: GetResultsUseCase,
        buyProductUseCase: BuyProductUseCase,
       getInventoryUseCase: GetInventoryUseCase
    ): GameViewModelFactory {
        return GameViewModelFactory(
            saveResultUseCase = saveResultUseCase,
            getResultsUseCase = getResultsUseCase,
            buyProductUseCase = buyProductUseCase,
            getInventoryUseCase = getInventoryUseCase,
        )
    }
}