package com.michael.nectargroceriesapp.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.domain.usecase.GetCategories
import com.michael.nectargroceriesapp.domain.usecase.ProductFilterRule
import com.michael.nectargroceriesapp.domain.usecase.applyFilterProducts
import com.michael.nectargroceriesapp.domain.usecase.cart.InsertCartItem
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterUseCases
import com.michael.nectargroceriesapp.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val filterUseCases: FilterUseCases,
    private val getCategories: GetCategories,
    private val insertCartItem: InsertCartItem
): ViewModel() {
    private val initialQuery: String = checkNotNull(savedStateHandle["query"])
    var query = mutableStateOf(initialQuery)
        private set
    var state by mutableStateOf<UiState<List<Product>>>(UiState.Loading)
        private set

    var filterRules = mutableStateMapOf<ProductFilterRule, Boolean>()

    init {
        loadProducts()
        loadCategories()
        loadFilterRules()
    }

    fun loadProducts() {
        state = UiState.Loading
        viewModelScope.launch {
            try {
                filterUseCases.filterProductsBySearch(query.value).collectLatest { result ->
                    state = UiState.Success(applyFilterProducts(
                        result,
                        ProductFilterRule.Or(filterRules.entries.filter { it.value }.map { it.key })
                    ))
                }
            } catch (e: Exception) {
                state = UiState.Error("Something went wrong: ${e.message}")
            }
        }
    }

    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            insertCartItem(Cart(productId = productId, count = quantity))
        }
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
    fun onQueryChange(newQuery: String) {
        query.value = newQuery
        if(newQuery.isEmpty()) {
            emptyProducts()
        } else {
            loadProducts()
        }
    }

    fun emptyProducts() {
        state = UiState.Success(emptyList())
    }

}