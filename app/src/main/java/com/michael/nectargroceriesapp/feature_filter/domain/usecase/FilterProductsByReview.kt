package com.michael.nectargroceriesapp.feature_filter.domain.usecase

import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import javax.inject.Inject

class FilterProductsByReview @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(review: Int) = productRepository.getProductsByReview(review)
}