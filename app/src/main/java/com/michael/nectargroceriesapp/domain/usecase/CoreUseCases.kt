package com.michael.nectargroceriesapp.domain.usecase

import javax.inject.Inject

data class CoreUseCases @Inject constructor(
    val getCategories: GetCategories,
    val getCategory: GetCategory,
    val getProduct: GetProduct
)