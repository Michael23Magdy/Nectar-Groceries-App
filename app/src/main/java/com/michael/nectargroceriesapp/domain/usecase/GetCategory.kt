package com.michael.nectargroceriesapp.domain.usecase

import com.michael.nectargroceriesapp.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategory @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(name: String) = categoryRepository.getCategoryById(name)
}