package com.michael.nectargroceriesapp.core.domain.usecase

import com.michael.nectargroceriesapp.core.data.local.ProductDao
import com.michael.nectargroceriesapp.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProduct @Inject constructor(
    private val productDao: ProductDao
) {
    operator fun invoke(id: Int): Flow<Product?> {
        return productDao.getProductById(id)
    }

}