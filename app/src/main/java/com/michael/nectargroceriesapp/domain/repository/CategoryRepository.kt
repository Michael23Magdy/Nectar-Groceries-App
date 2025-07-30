package com.michael.nectargroceriesapp.domain.repository

import com.michael.nectargroceriesapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
    fun getCategoryById(name: String): Flow<Category>
}