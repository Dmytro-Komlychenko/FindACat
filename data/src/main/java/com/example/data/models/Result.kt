package com.example.data.models

data class Result(
    var tryNumber: Int = 0,
    var countFoundCats: Int = 0
) {
    fun mapDataToDomain(): com.example.domain.models.Result {
        return com.example.domain.models.Result(
            tryNumber = tryNumber,
            countFoundCats = countFoundCats,
        )
    }

    companion object {
        fun mapDomainToData(domainAppConfig: com.example.domain.models.Result): Result {
            return Result(
                tryNumber = domainAppConfig.tryNumber,
                countFoundCats = domainAppConfig.countFoundCats,
            )
        }
    }
}