package com.michael.nectargroceriesapp.feature_product_details_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.michael.nectargroceriesapp.core.presentation.test.components.OutlinedNectarButton
import com.michael.nectargroceriesapp.core.presentation.test.components.TextNectarButton
import com.michael.nectargroceriesapp.feature_product_details_screen.presentation.components.Expandable
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.ui.theme.background
import com.michael.nectargroceriesapp.ui.theme.shapes

@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    viewModel: ProductDetailsViewModel = hiltViewModel()
){

    // TODO: Add screen state (loading, error, success)

    val product = viewModel.product
    val numberOfWantedUnits = viewModel.numberOfWantedUnits

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        Surface (
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface
        ){
            AsyncImage(
                model = product?.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(350.dp),
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_keyboard_arrow_left_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
//                        modifier = Modifier.size(40.dp)
                    )
                }
                TextButton(
                    onClick = {},
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
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = product?.name ?: "loading",
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = product?.detail ?: "loading",
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
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            NumberSelector(
                number = numberOfWantedUnits,
                onDecrease = { viewModel.decreaseNumberOfWantedUnits() },
                onIncrease = { viewModel.increaseNumberOfWantedUnits() },
            )
            Text(text = "$${product?.price}", style = MaterialTheme.typography.displaySmall)
        }

        HorizontalDivider(Modifier.padding(start = 20.dp, end = 20.dp), DividerDefaults.Thickness, MaterialTheme.colorScheme.surface)
        Expandable(
            header = "Product Detail",
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            Text(text = product?.description ?: "description", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onTertiary)
        }
        HorizontalDivider(Modifier.padding(start = 20.dp, end = 20.dp), DividerDefaults.Thickness, MaterialTheme.colorScheme.surface)
        Expandable(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 20.dp),
            header = "Nutritions",
            hint = {
                Text(
                    text = product?.nutritions["carbs"] ?: "xxx",
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
                product?.nutritions?.entries?.forEachIndexed{ index, (t, u) ->
                    val backgroundColor = if (index % 2 == 0) {
                        MaterialTheme.colorScheme.surface
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                    Row (modifier = Modifier.fillMaxWidth().background(backgroundColor).padding(12.dp), horizontalArrangement = Arrangement.SpaceAround) {
                        Text(text = t)
                        Text(text = u)
                    }
                }
            }
        }

        HorizontalDivider(Modifier.padding(start = 20.dp, end = 20.dp), DividerDefaults.Thickness, MaterialTheme.colorScheme.surface)
        Expandable(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 20.dp),
            header = "Review",
            hint = {
                Row {
                    repeat(product?.review ?: 4) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        ) {
            val totalStars = 5
            val filledStars = product?.review ?: 4
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                repeat(filledStars) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(50.dp))
                }
                repeat(totalStars - filledStars) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), modifier = Modifier.size(50.dp))
                }
            }
        }
    }

}

@Composable
fun NumberSelector(
    number: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextNectarButton(onClick = onDecrease){
            Icon(painter = painterResource(R.drawable.minus), contentDescription = null, tint = Color(0xFFB3B3B3))
        }
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedNectarButton(onClick = onIncrease) {
            Text(text = number.toString())
        }
        Spacer(modifier = Modifier.width(5.dp))
        TextNectarButton(onClick = onIncrease) {
            Icon(painter = painterResource(R.drawable.add), contentDescription = null)
        }
    }
}
