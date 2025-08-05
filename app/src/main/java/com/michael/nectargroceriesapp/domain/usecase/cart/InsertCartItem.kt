package com.michael.nectargroceriesapp.domain.usecase.cart

import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.repository.CartRepository
import javax.inject.Inject

class InsertCartItem @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cart: Cart) {
        val existingCartItem = cartRepository.getCartItems(cart.productId)
        var newCart = cart
        if (existingCartItem != null) {
            newCart = cart.copy(count = cart.count + existingCartItem.count, id = existingCartItem.id)
        }
        cartRepository.insertCartItem(newCart)
    }
}