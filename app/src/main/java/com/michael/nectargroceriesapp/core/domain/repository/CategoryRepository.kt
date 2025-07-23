package com.michael.nectargroceriesapp.core.domain.repository

import com.michael.nectargroceriesapp.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
}