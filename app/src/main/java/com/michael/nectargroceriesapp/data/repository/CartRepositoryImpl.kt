package com.michael.nectargroceriesapp.data.repository

import com.michael.nectargroceriesapp.data.local.CartDao
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.model.CartWithProduct
import com.michael.nectargroceriesapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
): CartRepository {
    override suspend fun insertCartItem(cart: Cart) {
        cartDao.insertCart(cart)
    }

    override suspend fun deleteCartItem(cart: Cart) {
        cartDao.deleteCart(cart)
    }

    override suspend fun deleteWholeCart() {
        cartDao.deleteWholeCart()
    }

    override fun getCartItems(): Flow<List<CartWithProduct>> {
        return cartDao.getCartWithProducts()
    }


}