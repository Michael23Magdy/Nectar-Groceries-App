package com.michael.nectargroceriesapp.presentation.screens.product_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.domain.usecase.core.GetProduct
import com.michael.nectargroceriesapp.domain.usecase.cart.InsertCartItem
import com.michael.nectargroceriesapp.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProduct: GetProduct,
    private val insertCartItem: InsertCartItem
): ViewModel() {
    val productId: Int = checkNotNull(savedStateHandle["productId"])
    var uiState by mutableStateOf<UiState<Product>>(UiState.Loading)
        private set
    var numberOfWantedUnits by mutableIntStateOf(1)
        private set

    init {
        loadProduct(productId)
    }
    fun loadProduct(id: Int){
        viewModelScope.launch {
            delay(500)
            getProduct(id).collect { product ->
                product?.let {
                    uiState = UiState.Success(product)
                } ?: run {
                    uiState = UiState.Error("Product not found")
                }
            }
        }
    }

    fun increaseNumberOfWantedUnits() {
        numberOfWantedUnits += 1
    }

    fun decreaseNumberOfWantedUnits() {
        if(numberOfWantedUnits > 1) {
            numberOfWantedUnits -= 1
        }
    }

    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            insertCartItem(Cart(productId = productId, count = quantity))
        }
    }
}

