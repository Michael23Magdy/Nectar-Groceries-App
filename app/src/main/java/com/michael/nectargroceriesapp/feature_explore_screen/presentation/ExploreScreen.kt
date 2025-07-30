package com.michael.nectargroceriesapp.feature_explore_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.feature_explore_screen.presentation.components.CategoryCard
import com.michael.nectargroceriesapp.feature_filter.presentation.components.SearchBar

@Composable
fun ExploreScreen(
    navController: NavHostController,
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val categories = viewModel.categories.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Find Products",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            textAlign = TextAlign.Center
        )
        SearchBar("", {}, modifier = Modifier.padding(20.dp, 0.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth().padding(20.dp),
        ) {
            items(categories.value) { category ->
                CategoryCard(category = category, navController::navigate)
            }
            items(categories.value) { category ->
                CategoryCard(category = category, navController::navigate)
            }
        }
    }
}