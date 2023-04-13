package com.example.data.models

import com.example.domain.models.ProductType

data class Product(
    val name: String = "",
    val price: Float = 0F,
    val imageUrl: Int = 0,
    val type: ProductType = ProductType.Cat,
    val position: Int = 0,
) {
    fun mapDataToDomain(): com.example.domain.models.Product {
        return com.example.domain.models.Product(
            name = name,
            price = price,
            imageUrl = imageUrl,
            type = type,
            position = position,
        )
    }

    companion object {
        fun mapDomainToData(domainProduct: com.example.domain.models.Product): Product {
            return Product(
                name = domainProduct.name,
                price = domainProduct.price,
                imageUrl = domainProduct.imageUrl,
                type = domainProduct.type,
                position = domainProduct.position,
            )
        }
    }
}
