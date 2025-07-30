package com.michael.nectargroceriesapp.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterUseCases
import com.michael.nectargroceriesapp.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val filterUseCases: FilterUseCases
): ViewModel() {
    private val initialQuery: String = checkNotNull(savedStateHandle["query"])
    var query = mutableStateOf(initialQuery)
        private set

    var state by mutableStateOf<UiState<List<Product>>>(UiState.Loading)
        private set

    init {
        loadProducts()
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
    fun loadProducts() {
        state = UiState.Loading
        viewModelScope.launch {
            try {
                filterUseCases.filterProductsBySearch(query.value).collectLatest { result ->
                    state = UiState.Success(result)
                }
            } catch (e: Exception) {
                state = UiState.Error("Something went wrong: ${e.message}")
            }
        }
    }
}