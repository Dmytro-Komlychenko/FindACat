package com.example.findacat.models

data class Result(
    val tryNumber: Int,
    val countFoundCats: Int
){
    fun mapPresentationToDomain(): com.example.domain.models.Result {
        return com.example.domain.models.Result(
            tryNumber = tryNumber,
            countFoundCats = countFoundCats,
        )
    }

    companion object {
        fun mapDomainToPresentation(domainAppConfig: com.example.domain.models.Result): Result {
            return Result(
                tryNumber = domainAppConfig.tryNumber,
                countFoundCats = domainAppConfig.countFoundCats,
            )
        }
    }
}