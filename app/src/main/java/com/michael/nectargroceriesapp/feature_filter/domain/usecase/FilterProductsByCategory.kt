package com.michael.nectargroceriesapp.feature_filter.domain.usecase

import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import javax.inject.Inject

class FilterProductsByCategory @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(categoryId: String) = productRepository.getProductsByCategory(categoryId)
}