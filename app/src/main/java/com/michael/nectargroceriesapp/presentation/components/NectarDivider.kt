package com.michael.nectargroceriesapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.michael.nectargroceriesapp.ui.theme.Dimen

@Composable
fun NectarDivider(){
    HorizontalDivider(Modifier.padding(start = Dimen.paddingLarge, end = Dimen.paddingLarge), DividerDefaults.Thickness, MaterialTheme.colorScheme.surface)
}