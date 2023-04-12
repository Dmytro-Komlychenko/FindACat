package com.example.data.network.firebase.realtime

import android.util.Log
import com.google.firebase.database.*
import com.example.data.models.Result
import com.example.data.repositories.DataGetResultsCallback


class FirebaseDB {

    private var linkDB: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(WEB_LINK_KEY)

    private var recordResultDB: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(RECORD_RESULT_KEY)

    fun logLink(webLink: String) {
        linkDB.push().setValue(webLink)
    }

    fun saveResult(result: Result) {
        recordResultDB.push().setValue(result)
    }

    fun getResults(dataGetResultsCallback: DataGetResultsCallback) {
        recordResultDB.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val results: ArrayList<Result> = arrayListOf()
                dataSnapshot.children.forEach { child ->
                    val result = child.getValue(Result::class.java)
                    result.let { results.add(it!!) }

                }
                dataGetResultsCallback.invoke(results)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(
                    "Firebase",
                    "-----ValueEventListener.onCancelled----- \n ${databaseError.message}"
                )
            }
        })
    }


    companion object {
        const val WEB_LINK_KEY = "WEB_LINK_KEY"
        const val RECORD_RESULT_KEY = "RECORD_RESULT_KEY"
    }
}