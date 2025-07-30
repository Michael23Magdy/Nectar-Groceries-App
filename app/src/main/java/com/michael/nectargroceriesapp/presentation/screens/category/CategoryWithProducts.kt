package com.michael.nectargroceriesapp.presentation.screens.category

import com.michael.nectargroceriesapp.domain.model.Category
import com.michael.nectargroceriesapp.domain.model.Product

data class CategoryWithProducts (
    val category: Category,
    val products: List<Product>
)