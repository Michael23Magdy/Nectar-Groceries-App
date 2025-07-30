package com.michael.nectargroceriesapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.ui.navigation.Routes

@Composable
fun SearchBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
){
    var query by rememberSaveable { mutableStateOf("") }
    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onNavigate = { navHostController.navigate(Routes.SearchScreen.createRoute(query)) },
        modifier = modifier
    )
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onNavigate: () -> Unit = {},
    modifier: Modifier = Modifier,
    placeholder: String = "Search Store"
) {
    val keyboardController = LocalSoftwareKeyboardController.current

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
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onNavigate()
                keyboardController?.hide()
            }
        ),
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
