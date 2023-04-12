package com.example.data.repositories

import com.example.data.network.firebase.realtime.FirebaseDB
import com.example.domain.repositoriesi.DatabaseRepositoryI

class DatabaseRepository(val firebaseDB: FirebaseDB) : DatabaseRepositoryI {

    override suspend fun logLink(webLink: String) {
        firebaseDB.logLink(webLink)
    }
}