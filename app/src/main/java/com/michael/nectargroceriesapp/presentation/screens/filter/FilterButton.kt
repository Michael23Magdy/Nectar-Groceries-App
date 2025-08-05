package com.michael.nectargroceriesapp.presentation.screens.filter

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.michael.nectargroceriesapp.R

@Composable
fun FilterButton(
    onClick: () -> Unit
){
    TextButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.filter),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}