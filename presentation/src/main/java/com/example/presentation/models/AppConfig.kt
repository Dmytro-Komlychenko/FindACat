package com.example.presentation.models


data class AppConfig(
    val gamePass: Boolean,
    val webLink: String
) {
    fun mapPresentationToDomain(): com.example.domain.models.AppConfig {
        return com.example.domain.models.AppConfig(
            gamePass = gamePass,
            webLink = webLink,
        )
    }

    companion object {
        fun mapDomainToPresentation(domainAppConfig: com.example.domain.models.AppConfig): AppConfig {
            return AppConfig(
                gamePass = domainAppConfig.gamePass,
                webLink = domainAppConfig.webLink,
            )
        }
    }
}