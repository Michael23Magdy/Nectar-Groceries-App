package com.michael.nectargroceriesapp.presentation.screens.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.usecase.CoreUseCases
import com.michael.nectargroceriesapp.domain.usecase.cart.InsertCartItem
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterUseCases
import com.michael.nectargroceriesapp.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    coreUseCases: CoreUseCases,
    filterUseCases: FilterUseCases,
    private val insertCartItem: InsertCartItem
): ViewModel() {
    val categoryId: String = checkNotNull(savedStateHandle["categoryId"])
    var categoryWithProductsUiState by mutableStateOf<UiState<CategoryWithProductsUiState>>(UiState.Loading)
        private set
    init {
        viewModelScope.launch {

            try {
                coreUseCases.getCategory(categoryId).collect { category ->
                    filterUseCases.filterProductsByCategory(categoryId).collect { products ->
                        categoryWithProductsUiState = UiState.Success(
                            CategoryWithProductsUiState(
                                category = category,
                                products = products
                            )
                        )
                    }
                }
            } catch (e: Exception){
                categoryWithProductsUiState = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            insertCartItem(Cart(productId = productId, count = quantity))
        }
    }
}
