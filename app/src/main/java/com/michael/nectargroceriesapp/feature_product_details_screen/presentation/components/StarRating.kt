package com.michael.nectargroceriesapp.feature_product_details_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.michael.nectargroceriesapp.ui.theme.orange

@Composable
fun StarRating(
    rating: Int,
    iconSize: Dp,
    totalStars: Int = 5,
    showEmptyStars: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(rating.coerceAtMost(totalStars)) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = orange,
                modifier = Modifier.size(iconSize)
            )
        }
        if (showEmptyStars) {
            repeat((totalStars - rating).coerceAtLeast(0)) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = orange.copy(alpha = 0.4f),
                    modifier = Modifier.size(iconSize)
                )
            }
        }
    }
}
