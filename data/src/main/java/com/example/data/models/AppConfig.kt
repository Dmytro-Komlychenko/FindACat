package com.example.data.models

data class AppConfig(
    val gamePass: Boolean,
    val webLink: String
) {
    fun mapDataToDomain(): com.example.domain.models.AppConfig {
        return com.example.domain.models.AppConfig(
            gamePass = gamePass,
            webLink = webLink,
        )
    }

    companion object {
        fun mapDomainToData(domainAppConfig: com.example.domain.models.AppConfig): AppConfig {
            return AppConfig(
                gamePass = domainAppConfig.gamePass,
                webLink = domainAppConfig.webLink,
            )
        }
    }
}