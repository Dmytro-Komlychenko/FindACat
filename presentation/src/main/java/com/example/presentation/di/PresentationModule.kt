package com.example.presentation.di

import com.example.domain.usecases.*
import com.example.presentation.ui.activities.main.config.ConfigViewModelFactory
import com.example.presentation.ui.activities.main.database.WebViewViewModelFactory
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
}