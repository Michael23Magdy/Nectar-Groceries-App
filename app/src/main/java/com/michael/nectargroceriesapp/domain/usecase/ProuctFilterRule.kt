package com.michael.nectargroceriesapp.domain.usecase

import com.michael.nectargroceriesapp.domain.model.Product

sealed class ProductFilterRule {
    abstract fun matches(product: Product): Boolean

    data class Category(val categoryId: String) : ProductFilterRule() {
        override fun matches(product: Product): Boolean = product.category == categoryId
    }

    data class Review(val review: Int): ProductFilterRule() {
        override fun matches(product: Product): Boolean = product.review == review
    }

    data class PriceRange(val min: Double, val max: Double) : ProductFilterRule() {
        override fun matches(product: Product): Boolean = product.price in min..max
    }

    data class Or(val rules: List<ProductFilterRule>) : ProductFilterRule() {
        override fun matches(product: Product): Boolean = if(rules.isEmpty()) true else rules.any { it.matches(product) }
    }
}

fun applyFilterProducts(products: List<Product>, rule: ProductFilterRule): List<Product> {
    return products.filter { rule.matches(it) }
}

fun ProductFilterRule.displayName(): String {
    return when (this) {
        is ProductFilterRule.Category -> "Category: ${categoryId}"
        is ProductFilterRule.PriceRange -> "Price: ${min} - ${max}"
        is ProductFilterRule.Review -> "Review: ${review}"
        else -> ""
    }
}
