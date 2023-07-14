package com.example.findacat.ui.fragments.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.*

class SplashViewModelFactory(
    private val getIsFirstLaunchUseCase: GetIsFirstLaunchUseCase,
    private val getAppActionFromServerUseCase: GetAppActionFromServerUseCase,
    private val getAppActionFromDBUseCase: GetAppActionFromDBUseCase,
    private val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase,
    private val saveAppActionToDBUseCase: SaveAppActionToDBUseCase,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(
            getIsFirstLaunchUseCase = getIsFirstLaunchUseCase,
            getAppActionFromServerUseCase = getAppActionFromServerUseCase,
            getAppActionFromDBUseCase = getAppActionFromDBUseCase,
            setIsFirstLaunchUseCase = setIsFirstLaunchUseCase,
            saveAppActionToDBUseCase = saveAppActionToDBUseCase,
        ) as T
    }
}