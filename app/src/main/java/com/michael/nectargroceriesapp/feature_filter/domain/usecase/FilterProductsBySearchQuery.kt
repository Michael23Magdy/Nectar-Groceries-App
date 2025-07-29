package com.michael.nectargroceriesapp.feature_filter.domain.usecase

import com.michael.nectargroceriesapp.core.domain.model.Product
import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterProductsBySearchQuery @Inject constructor (
    private val productRepository: ProductRepository
) {
    operator fun invoke(query: String): Flow<List<Product>> {
        val queryWithWildcards = "%$query%"
        return productRepository.getProductsBySearchQuery(queryWithWildcards)
    }
}