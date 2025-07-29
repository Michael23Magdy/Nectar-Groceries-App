package com.michael.nectargroceriesapp.feature_filter.domain.usecase

import javax.inject.Inject

data class FilterUseCases @Inject constructor(
    val filterProductsByPrice: FilterProductsByPrice,
    val filterProductsByReview: FilterProductsByReview,
    val filterProductsByCategory: FilterProductsByCategory,
    val filterProductsBySearch: FilterProductsBySearchQuery
)