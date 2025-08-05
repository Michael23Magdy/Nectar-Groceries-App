package com.michael.nectargroceriesapp.domain.usecase.filter

import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.domain.repository.ProductRepository
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