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

    override fun getProductsByCategory(categoryId: String): Flow<List<Product>> {
        return productDao.getProductsByCategory(categoryId)
    }

    override fun getProductsBySearchQuery(query: String): Flow<List<Product>> {
        return productDao.getProductsBySearchQuery(query)
    }

    override fun getProductsByPriceRange(
        minPrice: Double,
        maxPrice: Double
    ): Flow<List<Product>> {
        return productDao.getProductsByPriceRange(minPrice, maxPrice)
    }

    override fun getProductsByReview(review: Int): Flow<List<Product>> {
        return productDao.getProductsByReview(review)
    }
}