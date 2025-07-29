package com.michael.nectargroceriesapp.feature_filter.domain.usecase

import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import javax.inject.Inject

class FilterProductsByPrice @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(minPrice: Double, maxPrice: Double) =
        productRepository.getProductsByPriceRange(minPrice, maxPrice)
}