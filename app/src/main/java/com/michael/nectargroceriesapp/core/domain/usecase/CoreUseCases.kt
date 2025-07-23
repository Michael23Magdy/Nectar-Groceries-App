package com.michael.nectargroceriesapp.core.domain.usecase

import javax.inject.Inject

data class CoreUseCases @Inject constructor(
    val getCategories: GetCategories,
    val getProduct: GetProduct
)