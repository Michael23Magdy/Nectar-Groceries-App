package com.michael.nectargroceriesapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NectarDivider(){
    HorizontalDivider(Modifier.padding(start = 20.dp, end = 20.dp), DividerDefaults.Thickness, MaterialTheme.colorScheme.surface)
}