package com.michael.nectargroceriesapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.usecase.cart.InsertCartItem
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    filterUseCases: FilterUseCases,
    private val insertCartItem: InsertCartItem
): ViewModel() {
    val exclusiveOfferProductList = filterUseCases.filterProductsByPrice(0.0, 4.0).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    val bestSellingProductList = filterUseCases.filterProductsByReview(5).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    val meatAndFishProductsList = filterUseCases.filterProductsByCategory("Meat & Fish").stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            insertCartItem(Cart(productId = productId, count = quantity))
        }
    }
}