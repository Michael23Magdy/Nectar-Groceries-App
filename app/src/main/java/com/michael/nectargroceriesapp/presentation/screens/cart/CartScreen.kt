package com.michael.nectargroceriesapp.presentation.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.domain.model.CartWithProduct
import com.michael.nectargroceriesapp.presentation.components.EmptyList
import com.michael.nectargroceriesapp.presentation.components.NectarButton
import com.michael.nectargroceriesapp.presentation.components.NectarDivider
import com.michael.nectargroceriesapp.presentation.screens.UiState
import com.michael.nectargroceriesapp.presentation.screens.common.ErrorMessage
import com.michael.nectargroceriesapp.presentation.screens.common.LoadingIndicator
import com.michael.nectargroceriesapp.presentation.screens.product_details.components.NumberSelector
import com.michael.nectargroceriesapp.ui.navigation.Routes
import com.michael.nectargroceriesapp.ui.theme.Dimen

@Composable
fun CartScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel()
){
    val totalPrice = viewModel.totalPrice.collectAsState()
    val cartState = viewModel.state

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.paddingLarge)
        ) {
            Text(text = stringResource(R.string.my_cart), style = MaterialTheme.typography.titleLarge)
        }
        HorizontalDivider()
        when(cartState){
            is UiState.Loading -> LoadingIndicator()
            is UiState.Error -> ErrorMessage(cartState.message)
            is UiState.Success<List<CartWithProduct>> -> {
                if(cartState.data.isEmpty()){
                    EmptyList(stringResource(R.string.your_cart_is_empty))
                } else {
                    LazyColumn (
                        modifier = Modifier.weight(1f)
                    ) {
                        items(cartState.data) { item ->
                            CartItem(
                                cartWithProduct = item,
                                viewModel = viewModel
                            )
                            NectarDivider()
                        }
                    }
                }
            }
        }

        NectarButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(Dimen.paddingLarge),
            enabled = cartState is UiState.Success<List<CartWithProduct>> && cartState.data.isNotEmpty(),
            onClick = {
                navHostController.navigate(Routes.OrderAcceptedScreen.route)
                viewModel.deleteWholeCart()
            }
        ) {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp, 0.dp)
            ) {
                Text(
                    text = stringResource(R.string.go_to_checkout),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(Dimen.paddingLarge))
                Text(
                    text = "$%.2f".format(totalPrice.value),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(Color(0x44000000), shape = MaterialTheme.shapes.medium)
                        .padding(6.dp, 4.dp)
                )
            }
        }

    }
}

@Composable
fun CartItem(
    cartWithProduct: CartWithProduct,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel()
) {
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(Dimen.paddingLarge)
    ){
        AsyncImage(
            model = cartWithProduct.product.imageUrl,
            contentDescription = cartWithProduct.product.name,
            modifier = Modifier
                .height(120.dp)
                .width(90.dp),
            contentScale = ContentScale.Inside
        )
        Spacer(
            modifier = Modifier.padding(5.dp)
        )
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = cartWithProduct.product.name,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = cartWithProduct.product.detail,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { viewModel.onDelete(cartWithProduct.cart) },
                ) { Icon(Icons.Default.Clear, contentDescription = null) }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimen.paddingLarge),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                NumberSelector(
                    number = cartWithProduct.cart.count,
                    onDecrease = { viewModel.decreaseNumberOfWantedUnits(cartWithProduct.cart) },
                    onIncrease = { viewModel.increaseNumberOfWantedUnits(cartWithProduct.cart) },
                    invertBoarder = true,
                    modifier = Modifier.size(35.dp)
                )
                Text(text = "$${cartWithProduct.product.price}", style = MaterialTheme.typography.titleMedium)
            }
        }

    }
}