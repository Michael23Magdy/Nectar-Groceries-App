package com.michael.nectargroceriesapp.domain.usecase.filter

import com.michael.nectargroceriesapp.domain.repository.ProductRepository
import javax.inject.Inject

class FilterProductsByCategory @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(categoryId: String) = productRepository.getProductsByCategory(categoryId)
}