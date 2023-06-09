package com.example.data.network.firebase.realtime

import android.util.Log
import com.example.data.models.Product
import com.google.firebase.database.*
import com.example.data.models.Result
import com.example.data.repositories.DataGetInventoryCallback
import com.example.data.repositories.DataGetResultsCallback

class FirebaseDB {

    private var linkDB: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(WEB_LINK_KEY)

    private var recordResultDB: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(RECORD_RESULT_KEY)

    private var shopDB: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(SHOP_KEY)

    private var inventoryDB: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(INVENTORY_KEY)

    private var moneyDB: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(MONEY_KEY)

    /**
     * This method is used to log a last opened link
     */
    fun logLink(webLink: String) {
        linkDB.push().setValue(webLink)
    }

    /**
     * This method is used to save game result into a firebase's history
     */
    fun saveResult(result: Result) {
        recordResultDB.push().setValue(result)
    }

    /**
     * This method is used to get a game history
     */
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
                    TAG,
                    "-----RecordResultDB.onCancelled----- \n ${databaseError.message}"
                )
            }
        })
    }

    /**
     * This method is used to get bought items
     */
    fun getInventory(getInventoryCallback: DataGetInventoryCallback) {
        inventoryDB.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val products: ArrayList<Product> = arrayListOf()
                dataSnapshot.children.forEach { child ->
                    val result = child.getValue(Product::class.java)
                    result.let { products.add(it!!) }

                }
                getInventoryCallback.invoke(products)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(
                    TAG,
                    "-----InventoryDB.onCancelled----- \n ${databaseError.message}"
                )
            }
        })
    }

    /**
     * This method is used to save a bought product
     */
    fun buyProduct(product: Product) {
        shopDB.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val productToBuy =
                    dataSnapshot.children.find { prod -> prod.getValue(Product::class.java)?.name == product.name }

                productToBuy.let {
                    inventoryDB.push().setValue(product)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(
                    TAG,
                    "-----ValueEventListener.onCancelled----- \n ${databaseError.message}"
                )
            }
        })
    }

    /**
     * This method is used to save money count
     */
    fun updateMoney(money: Float) {
        val updates = hashMapOf<String, Any>()
        updates[MONEY_KEY] = money
        moneyDB.updateChildren(updates)
    }

    companion object {
        const val TAG = "FirebaseDB"
        const val WEB_LINK_KEY = "WEB_LINK_KEY"
        const val RECORD_RESULT_KEY = "RECORD_RESULT_KEY"
        const val SHOP_KEY = "SHOP_KEY"
        const val INVENTORY_KEY = "INVENTORY_KEY"
        const val MONEY_KEY = "MONEY_KEY"
    }
}