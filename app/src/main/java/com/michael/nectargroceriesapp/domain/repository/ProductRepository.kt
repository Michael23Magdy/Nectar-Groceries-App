package com.michael.nectargroceriesapp.domain.repository

import com.michael.nectargroceriesapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    fun getProductById(id: Int): Flow<Product>

    fun getProductsByCategory(categoryId: String): Flow<List<Product>>
    fun getProductsBySearchQuery(query: String): Flow<List<Product>>
    fun getProductsByPriceRange(minPrice: Double, maxPrice: Double): Flow<List<Product>>
    fun getProductsByReview(review: Int): Flow<List<Product>>
}