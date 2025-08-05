package com.michael.nectargroceriesapp.presentation.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.presentation.components.FilterButton
import com.michael.nectargroceriesapp.presentation.components.LazyTwoColVerticalGrid
import com.michael.nectargroceriesapp.presentation.components.ProductCard
import com.michael.nectargroceriesapp.presentation.screens.UiState
import com.michael.nectargroceriesapp.presentation.screens.common.ErrorMessage
import com.michael.nectargroceriesapp.presentation.screens.common.LoadingIndicator
import com.michael.nectargroceriesapp.ui.navigation.BackButton

@Composable
fun CategoryScreenRoot(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel()
){
    val uiState = viewModel.categoryWithProductsUiState
    when(uiState){
        is UiState.Loading -> LoadingIndicator()
        is UiState.Error -> ErrorMessage(uiState.message)
        is UiState.Success<CategoryWithProductsUiState> -> CategoryScreen(navController, uiState.data, modifier, viewModel)
    }

}

@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryWithProductsUiState: CategoryWithProductsUiState,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel()
){
    val category = categoryWithProductsUiState.category
    val products = categoryWithProductsUiState.products
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ) {
            BackButton(navController)
            Text(
                text = category.displayName,
                style = MaterialTheme.typography.titleLarge
            )
            FilterButton({})

        }
        LazyTwoColVerticalGrid {
            items(products) { item ->
                ProductCard(item, navController::navigate, onAdd = viewModel::addToCart, modifier = Modifier.fillMaxWidth())
            }
        }
    }

}