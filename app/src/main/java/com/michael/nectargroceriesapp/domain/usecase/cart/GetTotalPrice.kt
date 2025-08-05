package com.michael.nectargroceriesapp.domain.usecase.cart

import com.michael.nectargroceriesapp.domain.repository.CartRepository
import javax.inject.Inject

class GetTotalPrice @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke() = cartRepository.getTotalPrice()
}