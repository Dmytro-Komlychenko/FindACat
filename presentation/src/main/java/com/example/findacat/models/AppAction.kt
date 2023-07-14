package com.example.findacat.models

enum class AppActionE(val id: Int) {
    GAME(0),
    WEB(1),
}

data class AppAction(val value: AppActionE) {

    fun mapPresentationToDomain(): com.example.domain.models.AppAction {
        val appAction =
            when (value) {
                AppActionE.GAME -> com.example.domain.models.AppActionE.GAME
                AppActionE.WEB -> com.example.domain.models.AppActionE.WEB
            }
        return com.example.domain.models.AppAction(
            value = appAction,
        )
    }

    companion object {
        fun mapDomainToPresentation(domainAppConfig: com.example.domain.models.AppAction): AppAction {
            val appAction =
                when (domainAppConfig.value) {
                    com.example.domain.models.AppActionE.GAME -> AppActionE.GAME
                    com.example.domain.models.AppActionE.WEB -> AppActionE.WEB
                }
            return AppAction(
                value = appAction
            )
        }
    }
}