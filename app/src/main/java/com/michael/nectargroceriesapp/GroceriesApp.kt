package com.michael.nectargroceriesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.michael.nectargroceriesapp.ui.theme.NectarGroceriesAppTheme

@Composable
fun GroceriesApp(
    modifier: Modifier = Modifier,
) {
    NectarGroceriesAppTheme {
        Scaffold (
            modifier = modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "Hello World")
            }
        }

    }
}