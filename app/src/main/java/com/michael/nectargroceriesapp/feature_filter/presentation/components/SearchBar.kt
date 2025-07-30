package com.michael.nectargroceriesapp.feature_filter.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search Store"
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.onTertiary
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFFF2F3F2),
            unfocusedIndicatorColor = Color(0xFFF2F3F2),
            disabledIndicatorColor = Color(0xFFF2F3F2)
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .fillMaxWidth()
    )
}
