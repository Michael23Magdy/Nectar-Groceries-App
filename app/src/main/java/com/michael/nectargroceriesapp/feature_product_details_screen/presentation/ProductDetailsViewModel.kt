package com.michael.nectargroceriesapp.feature_product_details_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.core.domain.model.Product
import com.michael.nectargroceriesapp.core.domain.usecase.GetProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProduct: GetProduct
): ViewModel() {
    val productId: Int = checkNotNull(savedStateHandle["productId"])
    var product by mutableStateOf<Product?>(null)
        private set
    var numberOfWantedUnits by mutableStateOf(1)
        private set

    init {
        loadProduct(productId)
    }
    fun loadProduct(id: Int){
        viewModelScope.launch {
            getProduct(id).collect { result ->
                product = result
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
}