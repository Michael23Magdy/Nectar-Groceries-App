package com.michael.nectargroceriesapp.core.data.local

import androidx.room.Dao
import androidx.room.Query
import com.michael.nectargroceriesapp.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>
}