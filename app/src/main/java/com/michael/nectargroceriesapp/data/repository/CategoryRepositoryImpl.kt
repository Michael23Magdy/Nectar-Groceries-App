package com.michael.nectargroceriesapp.data.repository

import com.michael.nectargroceriesapp.data.local.CategoryDao
import com.michael.nectargroceriesapp.domain.model.Category
import com.michael.nectargroceriesapp.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }
}