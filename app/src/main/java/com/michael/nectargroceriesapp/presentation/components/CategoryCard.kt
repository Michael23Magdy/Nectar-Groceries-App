package com.michael.nectargroceriesapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.michael.nectargroceriesapp.domain.model.Category
import com.michael.nectargroceriesapp.ui.navigation.Routes
import com.michael.nectargroceriesapp.ui.theme.categoryCardColors

@Composable
fun CategoryCard(
    category: Category,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val color = categoryCardColors.random()
    Card (
        modifier = modifier.fillMaxWidth()
            .height(180.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(1.dp, color.copy(alpha = 0.7f)),
        onClick = { onClick(Routes.CategoryScreen.createRoute(category.name)) }
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                modifier = Modifier.height(80.dp),
                contentScale = ContentScale.Inside
            )
            Text(
                text = category.displayName,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}