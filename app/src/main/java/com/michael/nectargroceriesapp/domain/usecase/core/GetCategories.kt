package com.michael.nectargroceriesapp.domain.usecase.core

import com.michael.nectargroceriesapp.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke() = categoryRepository.getAllCategories()
}