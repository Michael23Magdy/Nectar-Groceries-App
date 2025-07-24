package com.michael.nectargroceriesapp.core.presentation.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.core.domain.usecase.CoreUseCases
import com.michael.nectargroceriesapp.core.domain.usecase.GetCategories
import com.michael.nectargroceriesapp.core.domain.usecase.GetProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
): ViewModel() {
    val categories = coreUseCases.getCategories().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

val product = coreUseCases.getProduct(1).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}