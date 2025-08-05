package com.michael.nectargroceriesapp.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import com.michael.nectargroceriesapp.domain.usecase.ProductFilterRule
import com.michael.nectargroceriesapp.domain.usecase.displayName
import com.michael.nectargroceriesapp.presentation.components.EmptyList
import com.michael.nectargroceriesapp.presentation.components.FilterButton
import com.michael.nectargroceriesapp.presentation.components.LazyTwoColVerticalGrid
import com.michael.nectargroceriesapp.presentation.components.NectarButton
import com.michael.nectargroceriesapp.presentation.components.ProductCard
import com.michael.nectargroceriesapp.presentation.components.SearchBar
import com.michael.nectargroceriesapp.presentation.screens.UiState
import com.michael.nectargroceriesapp.presentation.screens.common.ErrorMessage
import com.michael.nectargroceriesapp.presentation.screens.common.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query = viewModel.query
    val state = viewModel.state
    val filterRules = viewModel.filterRules

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(20.dp, 0.dp)
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



        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                FilterBottomSheet(
                    "Categories",
                    filterRules = filterRules.filter { it.key is ProductFilterRule.Category },
                    onRuleChange = { rule, isChecked ->
                        viewModel.updateFilterRule(rule, isChecked)
                    }
                )
                FilterBottomSheet(
                    "Prices",
                    filterRules = filterRules
                        .filter { it.key is ProductFilterRule.PriceRange }
                        .toSortedMap( compareBy { (it as ProductFilterRule.PriceRange).min } ),
                    onRuleChange = { rule, isChecked ->
                        viewModel.updateFilterRule(rule, isChecked)
                    }
                )
                FilterBottomSheet(
                    "Review",
                    filterRules = filterRules
                        .filter { it.key is ProductFilterRule.Review }
                        .toSortedMap( compareBy { (it as ProductFilterRule.Review).review } ),
                    onRuleChange = { rule, isChecked ->
                        viewModel.updateFilterRule(rule, isChecked)
                    }
                )

                NectarButton(
                    onClick = {
                        viewModel.loadProducts()
                        showBottomSheet = false
                              },
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                ) {
                    Text(text = "Apply Filter")
                }
            }
        }



    }
}

@Composable
fun FilterBottomSheet(
    title: String,
    filterRules: Map<ProductFilterRule, Boolean>,
    onRuleChange: (ProductFilterRule, Boolean) -> Unit
) {
    if (filterRules.isEmpty()) return
    Column(modifier = Modifier.padding(16.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        filterRules.forEach { (rule, isSelected) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { onRuleChange(rule, it) },
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = rule.displayName(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
