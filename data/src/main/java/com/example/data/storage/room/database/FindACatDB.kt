package com.example.data.storage.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.storage.room.dao.ProductDao
import com.example.data.storage.room.dao.ResultDao
import com.example.data.storage.room.entities.Result
import com.example.data.storage.room.entities.Product


@Database(entities = [Result::class, Product::class], version = 1, exportSchema = false)
abstract class FindACatDB: RoomDatabase() {

    abstract fun resultDao(): ResultDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: FindACatDB? = null

        fun getDB(context: Context): FindACatDB {
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FindACatDB::class.java,
                    "user_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}