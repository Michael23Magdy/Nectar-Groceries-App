package com.michael.nectargroceriesapp.domain.repository

import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.model.CartWithProduct
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun insertCartItem(cart: Cart)
    suspend fun deleteCartItem(cart: Cart)
    suspend fun deleteWholeCart()
    fun getCartItems(): Flow<List<CartWithProduct>>
}