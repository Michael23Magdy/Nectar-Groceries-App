package com.michael.nectargroceriesapp.feature_home_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.feature_filter.domain.usecase.FilterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filterUseCases: FilterUseCases
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
}