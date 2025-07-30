package com.michael.nectargroceriesapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NectarButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(45.dp),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(0.dp)
    ) {
        content()
    }
}

@Composable
fun OutlinedNectarButton(
    onClick: () -> Unit,
    outlineColor: Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .size(45.dp),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(0.5.dp, outlineColor)
    ) {
        content()
    }
}

@Composable
fun TextNectarButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .size(45.dp),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(0.dp)
    ) {
        content()
    }
}