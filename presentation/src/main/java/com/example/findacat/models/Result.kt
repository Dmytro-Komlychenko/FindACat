package com.example.findacat.models

data class Result(
    val tryNumber: Int,
    val countFoundCats: Int
) {

    companion object {
        fun mapDomainToPresentation(domainAppConfig: com.example.domain.models.Result): Result {
            return Result(
                tryNumber = domainAppConfig.tryNumber,
                countFoundCats = domainAppConfig.countFoundCats,
            )
        }
    }
}