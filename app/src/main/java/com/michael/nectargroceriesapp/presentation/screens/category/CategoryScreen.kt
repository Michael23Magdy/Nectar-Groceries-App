package com.michael.nectargroceriesapp.presentation.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.presentation.components.EmptyList
import com.michael.nectargroceriesapp.presentation.screens.filter.FilterButton
import com.michael.nectargroceriesapp.presentation.components.LazyTwoColVerticalGrid
import com.michael.nectargroceriesapp.presentation.components.ProductCard
import com.michael.nectargroceriesapp.presentation.screens.UiState
import com.michael.nectargroceriesapp.presentation.screens.common.ErrorMessage
import com.michael.nectargroceriesapp.presentation.screens.common.LoadingIndicator
import com.michael.nectargroceriesapp.presentation.screens.filter.FilterBottomSheet
import com.michael.nectargroceriesapp.ui.navigation.BackButton

@Composable
fun CategoryScreenRoot(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel()
){
    val uiState = viewModel.categoryWithProductsUiState
    when(uiState){
        is UiState.Loading -> LoadingIndicator()
        is UiState.Error -> ErrorMessage(uiState.message)
        is UiState.Success<CategoryWithProductsUiState> -> CategoryScreen(navHostController, uiState.data, modifier, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navHostController: NavHostController,
    categoryWithProductsUiState: CategoryWithProductsUiState,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel()
){
    val category = categoryWithProductsUiState.category
    val products = categoryWithProductsUiState.products

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            BackButton(navHostController)
            Text(
                text = category.displayName,
                style = MaterialTheme.typography.titleLarge
            )
            FilterButton(onClick = { showBottomSheet = true })

        }
        if(products.isEmpty()){
            EmptyList(stringResource(R.string.no_products_found))
        } else {
            LazyTwoColVerticalGrid {
                items(products) { item ->
                    ProductCard(item, navHostController::navigate, onAdd = viewModel::addToCart, modifier = Modifier.fillMaxWidth())
                }
            }
        }

        FilterBottomSheet(
            showBottomSheet = showBottomSheet,
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            onChange = {
                viewModel.updateFilterRule(it)
                showBottomSheet = false
            }
        )
    }

}