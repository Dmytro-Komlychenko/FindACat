package com.example.findacat.models

data class IsFirstLaunch(
    var value: Boolean,
) {
    fun mapPresentationToDomain(): com.example.domain.models.IsFirstLaunch {
        return com.example.domain.models.IsFirstLaunch(
            value = value,
        )
    }

    companion object {
        fun mapDomainToPresentation(domainAppConfig: com.example.domain.models.IsFirstLaunch): IsFirstLaunch {
            return IsFirstLaunch(
                value = domainAppConfig.value,
            )
        }
    }
}