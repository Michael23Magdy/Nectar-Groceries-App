package com.michael.nectargroceriesapp.feature_home_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.core.domain.model.Product
import com.michael.nectargroceriesapp.feature_home_screen.presentation.components.ProductCard

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val products = viewModel.products.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeScreenSection("Exclusive Offer", products.value)
        HomeScreenSection("Exclusive Offer", products.value)
        HomeScreenSection("Exclusive Offer", products.value)
        HomeScreenSection("Exclusive Offer", products.value)
    }
}

@Composable
fun HomeScreenSection(
    name: String,
    list: List<Product>
){
    Column {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = name)
            Text(text = "see all")
        }

        LazyRow {
            item{Spacer(modifier = Modifier.width(20.dp))}
            items(list) { item ->
                ProductCard(product = item)
                Spacer(modifier = Modifier.width(15.dp))
            }
            item{Spacer(modifier = Modifier.width(5.dp))}

        }
    }
}