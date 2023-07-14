package com.example.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.room.entities.Result

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveResult(result: Result)

    @Query ("SELECT * FROM result_table ORDER BY id ASC")
    suspend fun readAllData() : List<Result>
}