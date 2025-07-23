package com.michael.nectargroceriesapp.core.presentation.test

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TestScreen(
    viewModel: TestViewModel = hiltViewModel()
) {
    val categories = viewModel.categories.collectAsState()
    val product = viewModel.product.collectAsState()
    LazyColumn {
        item{
            Text(text = product.value.toString())
        }
        items(categories.value){ category ->
            Card {Text(text = category.toString())}
        }
    }
}