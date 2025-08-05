package com.michael.nectargroceriesapp.presentation.screens.filter

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.usecase.GetCategories
import com.michael.nectargroceriesapp.domain.usecase.ProductFilterRule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterBottomSheetViewModel @Inject constructor(
    private val getCategories: GetCategories,
): ViewModel() {
    var filterRules = mutableStateMapOf<ProductFilterRule, Boolean>()

    init {
        loadFilterRules()
    }

    fun loadFilterRules() {
        loadCategories()
        loadPrices()
        loadReviews()
    }

    fun loadCategories() {
        viewModelScope.launch {
            getCategories().collectLatest { categories ->
                val categoryFilters = categories.associate { category ->
                    ProductFilterRule.Category(category.name) to false
                }
                filterRules.putAll(categoryFilters)
            }
        }
    }

    fun loadPrices() {
        filterRules.putAll(listOf(
            ProductFilterRule.PriceRange(0.0, 4.0) to false,
            ProductFilterRule.PriceRange(4.0, 8.0) to false,
            ProductFilterRule.PriceRange(8.0, 12.0) to false
        ))
    }

    fun loadReviews() {
        filterRules.putAll(listOf(
            ProductFilterRule.Review(3) to false,
            ProductFilterRule.Review(4) to false,
            ProductFilterRule.Review(5) to false
        ))
    }

    fun updateFilterRule(rule: ProductFilterRule, isSelected: Boolean) {
        filterRules[rule] = isSelected
    }

    fun getRule(): ProductFilterRule{
        return ProductFilterRule.Or(filterRules.entries.filter { it.value }.map { it.key })
    }

}