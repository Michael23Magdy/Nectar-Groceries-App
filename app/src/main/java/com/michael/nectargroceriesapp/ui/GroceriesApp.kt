package com.michael.nectargroceriesapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.michael.nectargroceriesapp.ui.navigation.AppNavHost
import com.michael.nectargroceriesapp.ui.navigation.BottomBar
import com.michael.nectargroceriesapp.ui.theme.NectarGroceriesAppTheme

@Composable
fun GroceriesApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NectarGroceriesAppTheme(
        dynamicColor = false
    ) {
        Scaffold (
            bottomBar = { BottomBar(navController) },
            modifier = modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }

    }
}