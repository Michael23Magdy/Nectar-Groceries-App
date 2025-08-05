package com.michael.nectargroceriesapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.presentation.components.SearchBar
import com.michael.nectargroceriesapp.presentation.components.ProductCard
import com.michael.nectargroceriesapp.ui.theme.Dimen

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val exclusiveOfferProducts = viewModel.exclusiveOfferProductList.collectAsState()
    val bestSellingProducts = viewModel.bestSellingProductList.collectAsState()
    val meatAndFishProducts = viewModel.meatAndFishProductsList.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(Dimen.paddingLarge))
        HomeScreenHeader()
        Spacer(modifier = Modifier.size(Dimen.paddingLarge))
        SearchBar(
            navController,
            modifier = Modifier
                .padding(horizontal = Dimen.paddingLarge)
        )
        AdvertisementSection(Modifier.padding(Dimen.paddingLarge,Dimen.paddingLarge,Dimen.paddingLarge, 0.dp))

        HomeScreenSection(
            name = stringResource(R.string.exclusive_offer),
            list = exclusiveOfferProducts.value,
            safeNavigate = navController::navigate,
            onAdd = viewModel::addToCart
        )
        HomeScreenSection(
            name = stringResource(R.string.best_selling),
            list = bestSellingProducts.value,
            safeNavigate = navController::navigate,
            onAdd = viewModel::addToCart
        )
        HomeScreenSection(
            name = stringResource(R.string.meat_fish),
            list = meatAndFishProducts.value,
            safeNavigate = navController::navigate,
            onAdd = viewModel::addToCart
        )
    }
}

@Composable
fun HomeScreenHeader(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.nectar_logo_colored),
            contentDescription = stringResource(R.string.nectar_logo),
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.size(Dimen.paddingMedium))
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location Icon", tint = Color(0xFF4C4F4D))
            Text(text = "Dhaka, Banassre", style = MaterialTheme.typography.titleSmall, color = Color(0xFF4C4F4D))
        }
    }
}

@Composable
fun HomeScreenSection(
    name: String,
    list: List<Product>,
    modifier: Modifier = Modifier,
    safeNavigate: (String) -> Unit = {},
    onAdd: (Int, Int) -> Unit
){
    Column(
        modifier = modifier.padding(bottom = 6.dp, top = 6.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimen.paddingLarge),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(text = name, style = MaterialTheme.typography.titleLarge)
            Text(text = "see all", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
        }


        LazyRow {
            item{Spacer(modifier = Modifier.width(Dimen.paddingLarge))}
            items(list) { item ->
                ProductCard(product = item, onClick = safeNavigate, onAdd = onAdd)
                Spacer(modifier = Modifier.width(15.dp))
            }
            item{Spacer(modifier = Modifier.width(5.dp))}

        }
    }
}