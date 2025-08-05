package com.michael.nectargroceriesapp.presentation.screens.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.model.CartWithProduct
import com.michael.nectargroceriesapp.domain.usecase.cart.CartUseCases
import com.michael.nectargroceriesapp.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
): ViewModel() {
    var state by mutableStateOf<UiState<List<CartWithProduct>>>(UiState.Loading)
        private set

    init {
        loadCart()
    }

    private fun loadCart() {
        state = UiState.Loading
        viewModelScope.launch {
            try {
                cartUseCases.getCart().collectLatest { result ->
                    state = UiState.Success(result)
                }
            } catch (e: Exception) {
                state = UiState.Error("Something went wrong: ${e.message}")
            }
        }
    }

    val totalPrice = cartUseCases.getTotalPrice().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    fun increaseNumberOfWantedUnits(cart: Cart) {
        viewModelScope.launch {
            cartUseCases.insertCartItem(cart.copy(count = +1))
        }
    }

    fun decreaseNumberOfWantedUnits(cart: Cart) {
        viewModelScope.launch {
            if(cart.count > 1){
                cartUseCases.insertCartItem(cart.copy(count = -1))
            }
        }
    }

    fun onDelete(cart: Cart) {
        viewModelScope.launch {
            cartUseCases.deleteCartItem(cart)
        }
    }

    fun deleteWholeCart() {
        viewModelScope.launch {
            cartUseCases.deleteWholeCart()
        }
    }
}