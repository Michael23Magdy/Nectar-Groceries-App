package com.michael.nectargroceriesapp.domain.usecase.cart

import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.repository.CartRepository
import javax.inject.Inject

class InsertCartItem @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cart: Cart) {
        cartRepository.insertCartItem(cart)
    }
}