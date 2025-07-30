package com.michael.nectargroceriesapp.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.presentation.components.FilterButton
import com.michael.nectargroceriesapp.presentation.components.LazyTwoColVerticalGrid
import com.michael.nectargroceriesapp.presentation.components.ProductCard
import com.michael.nectargroceriesapp.presentation.components.SearchBar
import com.michael.nectargroceriesapp.presentation.screens.UiState
import com.michael.nectargroceriesapp.presentation.screens.common.ErrorMessage
import com.michael.nectargroceriesapp.presentation.screens.common.LoadingIndicator

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query = viewModel.query
    val state = viewModel.state

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(20.dp, 0.dp)
        ) {
            SearchBar(query.value, viewModel::onQueryChange, modifier = Modifier.weight(1f))
            FilterButton()
        }
        when(state){
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorMessage(state.message)
            is UiState.Success<List<Product>> -> {
                val products = state.data

                if (products.isEmpty()){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Search Results",
                            color = Color.Gray
                        )
                    }
                } else {
                    LazyTwoColVerticalGrid {
                        items(products){ item ->
                            ProductCard(item, navController::navigate, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }

            }
        }
    }
}