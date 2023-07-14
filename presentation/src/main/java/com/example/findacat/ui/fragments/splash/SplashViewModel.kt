package com.example.findacat.ui.fragments.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.*
import com.example.findacat.models.AppAction
import com.example.findacat.models.IsFirstLaunch
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getIsFirstLaunchUseCase: GetIsFirstLaunchUseCase,
    private val getAppActionFromServerUseCase: GetAppActionFromServerUseCase,
    private val getAppActionFromDBUseCase: GetAppActionFromDBUseCase,
    private val setIsFirstLaunchUseCase: SetIsFirstLaunchUseCase,
    private val saveAppActionToDBUseCase: SaveAppActionToDBUseCase,
) : ViewModel() {

    var isFirstLaunch: MutableLiveData<IsFirstLaunch> = MutableLiveData()
    var appAction: MutableLiveData<AppAction> = MutableLiveData()

    init {
        launchFirst()
    }

    private fun launchFirst() {
        viewModelScope.launch {
            getIsFirstLaunchUseCase.execute {
                isFirstLaunch.value = IsFirstLaunch.mapDomainToPresentation(it)
            }
        }
    }

    fun initAppAction() {
        viewModelScope.launch {
            if (isFirstLaunch.value!!.value) {
                isFirstLaunch.value!!.value = false

                getAppActionFromServerUseCase.execute {
                    appAction.value = AppAction.mapDomainToPresentation(it)
                }
                setIsFirstLaunchUseCase.execute()
                saveAppActionToDBUseCase.execute(appAction.value!!.mapPresentationToDomain())

            } else {
                getAppActionFromDBUseCase.execute {
                    appAction.value = AppAction.mapDomainToPresentation(it)
                }
            }
        }
    }
}