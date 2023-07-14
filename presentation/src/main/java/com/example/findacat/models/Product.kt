package com.example.findacat.models

import com.example.domain.models.ProductType

data class Product(
    val name: String,
    val price: Float,
    val imageUrl: Int,
    val type: ProductType,
    val position: Int,
) {
    fun mapPresentationToDomain(): com.example.domain.models.Product {
        return com.example.domain.models.Product(
            name = name,
            price = price,
            imageUrl = imageUrl,
            type = type,
            position = position,
        )
    }

    companion object {
        fun mapDomainToPresentation(domainProduct: com.example.domain.models.Product): Product {
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