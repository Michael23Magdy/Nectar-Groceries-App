package com.michael.nectargroceriesapp.presentation.screens.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.presentation.components.NectarButton
import com.michael.nectargroceriesapp.presentation.components.NectarDivider
import com.michael.nectargroceriesapp.presentation.screens.UiState
import com.michael.nectargroceriesapp.presentation.screens.common.ErrorMessage
import com.michael.nectargroceriesapp.presentation.screens.common.LoadingIndicator
import com.michael.nectargroceriesapp.presentation.screens.product_details.components.Expandable
import com.michael.nectargroceriesapp.presentation.screens.product_details.components.NumberSelector
import com.michael.nectargroceriesapp.presentation.screens.product_details.components.StarRating
import com.michael.nectargroceriesapp.ui.navigation.BackButton
import com.michael.nectargroceriesapp.ui.navigation.Routes
import com.michael.nectargroceriesapp.ui.navigation.safeNavigateSingleTopTo
import com.michael.nectargroceriesapp.ui.theme.Dimen

@Composable
fun ProductDetailsScreenRoot(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    when (uiState) {
        is UiState.Loading -> LoadingIndicator()
        is UiState.Error -> ErrorMessage(uiState.message)
        is UiState.Success<Product> -> ProductDetailsScreen(navController, uiState.data, modifier, viewModel)
    }
}
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    product: Product,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel()
){
    val numberOfWantedUnits = viewModel.numberOfWantedUnits

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Surface (
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface
        ){
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(350.dp),
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton(navController)
                val context = LocalContext.current
                TextButton(
                    onClick = { shareTextWithImageLink(context,
                        product.name, product.imageUrl)},
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_ios_share_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.paddingLarge),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.size(Dimen.paddingMedium))
                Text(
                    text = product.detail,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
            TextButton(onClick = {}) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.paddingLarge),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            NumberSelector(
                number = numberOfWantedUnits,
                onDecrease = { viewModel.decreaseNumberOfWantedUnits() },
                onIncrease = { viewModel.increaseNumberOfWantedUnits() },
            )
            Text(text = "$${product.price}", style = MaterialTheme.typography.displaySmall)
        }

        NectarDivider()
        ProductDescriptionSection(product.description)

        NectarDivider()
        NutritionSection(product.nutritions)

        NectarDivider()
        ReviewSection(product.review)

        Spacer(modifier = Modifier.weight(1f))
        NectarButton(
            onClick = {
                viewModel.addToCart(product.id, numberOfWantedUnits)
                navController.safeNavigateSingleTopTo(Routes.CartScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(Dimen.paddingLarge, 0.dp),
        ) {
            Text(text = stringResource(R.string.add_to_basket), style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(Dimen.paddingMedium))
        }
    }
}

@Composable
fun ProductDescriptionSection(description: String) {
    Expandable(
        header = stringResource(R.string.product_detail),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimen.paddingMedium, horizontal = Dimen.paddingLarge)
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiary)
    }
}
@Composable
fun ReviewSection(review: Int) {
    Expandable(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimen.paddingLarge, Dimen.paddingMedium),
        header = stringResource(R.string.review),
        hint = {
            StarRating(
                rating = review,
                iconSize = Dimen.paddingLarge
            )
        }
    ) {
        StarRating(
            rating = review,
            iconSize = 50.dp,
            showEmptyStars = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun NutritionSection(nutritions: Map<String, String>){
    Expandable(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimen.paddingLarge, Dimen.paddingMedium),
        header = stringResource(R.string.nutritions),
        hint = {
            Text(
                text = nutritions["carbs"] ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier =  Modifier
                    .background(
                        color = Color(0xFFEBEBEB),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(6.dp, 3.dp))
        }
    ) {
        Column {
            nutritions.entries.forEachIndexed{ index, (key, value) ->
                val backgroundColor = if (index % 2 == 0) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.background
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceAround)
                {
                    Text(text = key)
                    Text(text = value)
                }
            }
        }
    }
}