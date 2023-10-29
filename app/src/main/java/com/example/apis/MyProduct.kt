package com.example.apis

data class MyProduct(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)