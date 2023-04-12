package com.example.domain.repositoriesi

import com.example.domain.models.Result

typealias DomainGetResultsCallback = (ArrayList<Result>) -> Unit

interface DatabaseRepositoryI {
    suspend fun logLink(webLink: String)
    suspend fun saveResult(result: Result)
    suspend fun getResults(domainGetResultsCallback: DomainGetResultsCallback)
}