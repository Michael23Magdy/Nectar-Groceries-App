package com.michael.nectargroceriesapp.data.local

import androidx.room.Dao
import androidx.room.Query
import com.michael.nectargroceriesapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE name = :name")
    fun getCategoryById(name: String): Flow<Category>
}