package com.example.data.models

data class IsFirstLaunch(
    var value: Boolean,
) {
    fun mapDataToDomain(): com.example.domain.models.IsFirstLaunch {
        return com.example.domain.models.IsFirstLaunch(
            value = value,
        )
    }

    companion object {
        fun mapDomainToData(domainAppConfig: com.example.domain.models.IsFirstLaunch): IsFirstLaunch {
            return IsFirstLaunch(
                value = domainAppConfig.value,
            )
        }
    }
}