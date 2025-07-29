package com.michael.nectargroceriesapp.core.data.local

import androidx.room.Dao
import androidx.room.Query
import com.michael.nectargroceriesapp.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getProductById(id: Int): Flow<Product>

    @Query("SELECT * FROM products WHERE category = :categoryId")
    fun getProductsByCategory(categoryId: String): Flow<List<Product>>

    @Query("""
        SELECT * FROM products 
        WHERE name LIKE :query
           OR detail LIKE :query 
           OR description LIKE :query
    """)
    fun getProductsBySearchQuery(query: String): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE price BETWEEN :minPrice AND :maxPrice")
    fun getProductsByPriceRange(minPrice: Double, maxPrice: Double): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE review >= :review")
    fun getProductsByReview(review: Int): Flow<List<Product>>

}