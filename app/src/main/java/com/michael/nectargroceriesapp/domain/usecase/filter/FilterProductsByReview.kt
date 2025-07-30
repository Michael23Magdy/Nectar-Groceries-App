package com.michael.nectargroceriesapp.domain.usecase.filter

import com.michael.nectargroceriesapp.domain.repository.ProductRepository
import javax.inject.Inject

class FilterProductsByReview @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(review: Int) = productRepository.getProductsByReview(review)
}