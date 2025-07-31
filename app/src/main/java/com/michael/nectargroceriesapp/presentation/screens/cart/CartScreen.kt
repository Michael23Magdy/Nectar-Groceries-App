package com.michael.nectargroceriesapp.presentation.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.michael.nectargroceriesapp.domain.model.CartWithProduct
import com.michael.nectargroceriesapp.presentation.screens.product_details.components.NumberSelector

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
){
    val cart = viewModel.cart
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) {
            Text(text = "My Cart", style = MaterialTheme.typography.titleLarge)
        }
        HorizontalDivider()

        LazyColumn {
            items(cart.value) { item ->
                CartItem(
                    cartWithProduct = item,
                    viewModel = viewModel)
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
    Row {
        AsyncImage(
            model = cartWithProduct.product.imageUrl,
            contentDescription = cartWithProduct.product.name,
            modifier = Modifier.padding(5.dp)
        )
        Column {
            Row {
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
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                NumberSelector(
                    number = cartWithProduct.cart.count,
                    onDecrease = { viewModel.decreaseNumberOfWantedUnits(cartWithProduct.cart) },
                    onIncrease = { viewModel.increaseNumberOfWantedUnits(cartWithProduct.cart) },
                )
                Text(text = "$${cartWithProduct.product.price}", style = MaterialTheme.typography.displaySmall)
            }
        }

    }
}