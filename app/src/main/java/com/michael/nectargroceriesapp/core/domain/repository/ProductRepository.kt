package com.michael.nectargroceriesapp.core.domain.repository

import com.michael.nectargroceriesapp.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    fun getProductById(id: Int): Flow<Product>
}