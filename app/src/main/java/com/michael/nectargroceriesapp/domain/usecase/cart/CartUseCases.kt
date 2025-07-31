package com.michael.nectargroceriesapp.domain.usecase.cart

data class CartUseCases(
    val getCart: GetCart,
    val deleteCartItem: DeleteCartItem,
    val deleteWholeCart: DeleteWholeCart,
    val insertCartItem: InsertCartItem
)
