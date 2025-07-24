package com.michael.nectargroceriesapp.core.presentation.test

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TestScreen(
    name: String = "Test"
) {
    Text(
        text = name,
        fontSize = 60.sp
    )
}