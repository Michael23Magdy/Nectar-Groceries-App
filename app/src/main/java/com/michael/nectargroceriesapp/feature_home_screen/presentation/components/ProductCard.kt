package com.michael.nectargroceriesapp.feature_home_screen.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.michael.nectargroceriesapp.core.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .size(width = 150.dp, height = 200.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(1.dp, Color(0xFFE2E2E2)), // 👈 border here
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),

            ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier.height(80.dp),
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
                AddButton(onClick = {})
            }
        }
    }

}

@Composable
fun AddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(45.dp),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun previewCard(){
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
        )
    )
}