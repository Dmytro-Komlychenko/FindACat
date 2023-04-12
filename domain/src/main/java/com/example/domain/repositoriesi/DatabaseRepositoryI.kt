package com.example.domain.repositoriesi

interface DatabaseRepositoryI {
    suspend fun logLink(webLink: String)
}