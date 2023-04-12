package com.example.data.network.firebase.realtime

import com.google.firebase.database.*

class FirebaseDB {

    private var dataBase: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(WEB_LINK_KEY)

    fun logLink(webLink: String) {
        dataBase.push().setValue(webLink)
    }

    companion object {
        const val WEB_LINK_KEY = "WEB_LINK_KEY"
    }
}