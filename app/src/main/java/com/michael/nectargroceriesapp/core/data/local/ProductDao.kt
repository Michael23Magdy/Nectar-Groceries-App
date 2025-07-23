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
}