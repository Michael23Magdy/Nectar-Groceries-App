package com.michael.nectargroceriesapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.presentation.components.SearchBar
import com.michael.nectargroceriesapp.presentation.components.ProductCard

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val exclusiveOfferProducts = viewModel.exclusiveOfferProductList.collectAsState()
    val bestSellingProducts = viewModel.bestSellingProductList.collectAsState()
    val meatAndFishProducts = viewModel.meatAndFishProductsList.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        HomeScreenHeader()
        Spacer(modifier = Modifier.size(20.dp))
        SearchBar(
            query = "",
            onQueryChange = {},
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )
        //TODO: Panels
        HomeScreenSection("Exclusive Offer", exclusiveOfferProducts.value, navController::navigate)
        HomeScreenSection("Best Selling", bestSellingProducts.value, navController::navigate)
        HomeScreenSection("Meat & Fish", meatAndFishProducts.value, navController::navigate)
    }
}

@Composable
fun HomeScreenHeader(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.nectar_logo_colored),
        contentDescription = "Nectar Logo",
        modifier = Modifier.size(40.dp)
    )
    Spacer(modifier = Modifier.size(10.dp))
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location Icon", tint = Color(0xFF4C4F4D))
        Text(text = "Dhaka, Banassre", style = MaterialTheme.typography.titleSmall, color = Color(0xFF4C4F4D))
        // TODO: get real location
    }
}

@Composable
fun HomeScreenSection(
    name: String,
    list: List<Product>,
    safeNavigate: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(bottom = 6.dp, top = 6.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(text = name, style = MaterialTheme.typography.titleLarge)
            Text(text = "see all", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
        }


        LazyRow {
            item{Spacer(modifier = Modifier.width(20.dp))}
            items(list) { item ->
                ProductCard(product = item, onClick = safeNavigate)
                Spacer(modifier = Modifier.width(15.dp))
            }
            item{Spacer(modifier = Modifier.width(5.dp))}

        }
    }
}