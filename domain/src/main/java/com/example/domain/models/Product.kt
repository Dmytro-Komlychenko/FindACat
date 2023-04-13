package com.example.domain.models

enum class ProductType {
    Box, Cat
}

data class Product(
    val name: String,
    val price: Float,
    val imageUrl: Int,
    val type: ProductType,
    val position: Int,
)