package com.example.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.ProductType

@Entity(tableName = "product_table")
class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String = "",
    val price: Float = 0F,
    val imageUrl: Int = 0,
    val type: ProductType = ProductType.Cat,
    val position: Int = 0,
) {
    companion object {
        fun mapEntityToModel(product: Product): com.example.domain.models.Product {
            return com.example.domain.models.Product(
                product.name,
                product.price,
                product.imageUrl,
                product.type,
                product.position,
            )
        }

        fun mapModelToEntity(product: com.example.domain.models.Product): Product {
            return Product(
                0,
                product.name,
                product.price,
                product.imageUrl,
                product.type,
                product.position,
            )
        }
    }
}