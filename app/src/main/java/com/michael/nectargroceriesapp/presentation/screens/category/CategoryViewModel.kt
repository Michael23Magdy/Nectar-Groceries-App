package com.michael.nectargroceriesapp.presentation.screens.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.usecase.CoreUseCases
import com.michael.nectargroceriesapp.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    coreUseCases: CoreUseCases
): ViewModel() {
    val categoryId: String = checkNotNull(savedStateHandle["categoryId"])
    val category by mutableStateOf<UiState<CategoryWithProducts>>(UiState.Loading)
    init {
        viewModelScope.launch {
//            coreUseCases.getCategories().collect { category -> category
        }
    }
}
