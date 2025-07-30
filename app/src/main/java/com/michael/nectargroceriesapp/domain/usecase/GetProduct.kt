package com.michael.nectargroceriesapp.domain.usecase

import com.michael.nectargroceriesapp.data.local.ProductDao
import com.michael.nectargroceriesapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProduct @Inject constructor(
    private val productDao: ProductDao
) {
    operator fun invoke(id: Int): Flow<Product?> {
        return productDao.getProductById(id)
    }

}