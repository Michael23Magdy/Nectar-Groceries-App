package com.michael.nectargroceriesapp.presentation.screens.category

import com.michael.nectargroceriesapp.domain.model.Category
import com.michael.nectargroceriesapp.domain.model.Product

data class CategoryWithProductsUiState (
    val category: Category,
    val products: List<Product>
)