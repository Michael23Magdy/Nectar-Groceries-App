package com.michael.nectargroceriesapp.domain.usecase.filter

import javax.inject.Inject

data class FilterUseCases @Inject constructor(
    val filterProductsByPrice: FilterProductsByPrice,
    val filterProductsByReview: FilterProductsByReview,
    val filterProductsByCategory: FilterProductsByCategory,
    val filterProductsBySearch: FilterProductsBySearchQuery
)