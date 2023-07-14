package com.example.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_table")
class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var tryNumber: Int = 0,
    var countFoundCats: Int = 0
) {
    companion object {
        fun mapEntityToModel(result: Result): com.example.domain.models.Result {
            return com.example.domain.models.Result(result.tryNumber, result.countFoundCats)
        }

        fun mapModelToEntity(result: com.example.domain.models.Result): Result {
            return Result(0, result.tryNumber, result.countFoundCats)
        }
    }
}