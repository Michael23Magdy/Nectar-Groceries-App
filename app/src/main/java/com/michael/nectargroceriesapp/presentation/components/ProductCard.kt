package com.michael.nectargroceriesapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.michael.nectargroceriesapp.domain.model.Product
import com.michael.nectargroceriesapp.ui.navigation.Routes

@Composable
fun ProductCard(
    product: Product,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 150.dp
){
    Card(
        modifier = Modifier
            .wrapContentSize(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(1.dp, Color(0xFFE2E2E2)),
        onClick = { onClick(Routes.ProductScreen.createRoute(product.id)) }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .width(width)
                .aspectRatio(ratio = 0.75f)
                .padding(10.dp),

            ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = product.detail,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text = "$${product.price}")
                NectarButton(onClick = {}){
                    Icon(imageVector =  Icons.Default.Add, contentDescription = null)
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewCard(){
    ProductCard(
        product = Product(
            1,
            "test",
            "test",
            "https://www.diagnosisdiet.com/assets/images/6/fruit-og-z8vk4201s653efw.jpg",
            4.99,
            "test",
            "test",
            mapOf(
                "test" to "test",
                "test" to "test",
                "test" to "test"
            ),
            3
        ),

        {}
    )
}