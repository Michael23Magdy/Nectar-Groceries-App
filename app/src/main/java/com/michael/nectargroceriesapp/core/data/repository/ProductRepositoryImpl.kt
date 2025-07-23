package com.michael.nectargroceriesapp.core.data.repository

import com.michael.nectargroceriesapp.core.data.local.ProductDao
import com.michael.nectargroceriesapp.core.domain.model.Product
import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
): ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts()
    }

    override fun getProductById(id: Int): Flow<Product> {
        return productDao.getProductById(id)
    }
}