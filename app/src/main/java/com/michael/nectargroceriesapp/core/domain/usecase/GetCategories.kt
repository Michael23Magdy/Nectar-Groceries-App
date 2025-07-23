package com.michael.nectargroceriesapp.core.domain.usecase

import com.michael.nectargroceriesapp.core.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke() = categoryRepository.getAllCategories()
}