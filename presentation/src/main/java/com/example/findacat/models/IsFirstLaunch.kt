package com.example.findacat.models

data class IsFirstLaunch(
    var value: Boolean,
) {

    companion object {
        fun mapDomainToPresentation(domainAppConfig: com.example.domain.models.IsFirstLaunch): IsFirstLaunch {
            return IsFirstLaunch(
                value = domainAppConfig.value,
            )
        }
    }
}