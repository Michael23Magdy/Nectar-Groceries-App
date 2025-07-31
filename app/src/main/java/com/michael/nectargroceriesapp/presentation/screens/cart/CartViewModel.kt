package com.michael.nectargroceriesapp.presentation.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.usecase.cart.CartUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
): ViewModel() {
    val cart = cartUseCases.getCart().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun increaseNumberOfWantedUnits(cart: Cart) {
        viewModelScope.launch {
            cartUseCases.insertCartItem(cart.copy(count = cart.count + 1))
        }
    }

    fun decreaseNumberOfWantedUnits(cart: Cart) {
        viewModelScope.launch {
            if(cart.count > 1){
                cartUseCases.insertCartItem(cart.copy(count = cart.count - 1))
            }
        }
    }

    fun onDelete(cart: Cart) {
        viewModelScope.launch {
            cartUseCases.deleteCartItem(cart)
        }
    }
}