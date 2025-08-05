package com.michael.nectargroceriesapp.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.presentation.components.EmptyList
import com.michael.nectargroceriesapp.presentation.screens.filter.FilterButton
import com.michael.nectargroceriesapp.presentation.components.LazyTwoColVerticalGrid
import com.michael.nectargroceriesapp.presentation.components.ProductCard
import com.michael.nectargroceriesapp.presentation.components.SearchBar
import com.michael.nectargroceriesapp.presentation.screens.UiState
import com.michael.nectargroceriesapp.presentation.screens.common.ErrorMessage
import com.michael.nectargroceriesapp.presentation.screens.common.LoadingIndicator
import com.michael.nectargroceriesapp.presentation.screens.filter.FilterBottomSheet
import com.michael.nectargroceriesapp.ui.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query = viewModel.query
    val state = viewModel.state

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(Dimen.paddingLarge, 0.dp)
        ) {
            SearchBar(query.value, viewModel::onQueryChange, modifier = Modifier.weight(1f))
            FilterButton(onClick = { showBottomSheet = true })
        }
        when(state){
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorMessage(state.message)
            is UiState.Success<List<Product>> -> {
                val products = state.data

                if (products.isEmpty()){
                    EmptyList("No Search results")
                } else {
                    LazyTwoColVerticalGrid {
                        items(products){ item ->
                            ProductCard(item, navController::navigate, onAdd = viewModel::addToCart, modifier = Modifier.fillMaxWidth())
                        }
                    }
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
