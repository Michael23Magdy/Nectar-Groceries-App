package com.michael.nectargroceriesapp.domain.usecase.cart

import com.michael.nectargroceriesapp.domain.repository.CartRepository
import javax.inject.Inject

class DeleteWholeCart @Inject constructor(
    private val cartRepository: CartRepository
){
    suspend operator fun invoke() {
        cartRepository.deleteWholeCart()
    }
}