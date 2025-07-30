package com.michael.nectargroceriesapp.domain.usecase.filter

import com.michael.nectargroceriesapp.domain.repository.ProductRepository
import javax.inject.Inject

class FilterProductsByPrice @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(minPrice: Double, maxPrice: Double) =
        productRepository.getProductsByPriceRange(minPrice, maxPrice)
}