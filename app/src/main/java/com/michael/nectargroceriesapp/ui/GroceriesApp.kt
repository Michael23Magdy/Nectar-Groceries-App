package com.michael.nectargroceriesapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.michael.nectargroceriesapp.ui.navigation.AppNavHost
import com.michael.nectargroceriesapp.ui.navigation.BottomBar
import com.michael.nectargroceriesapp.ui.navigation.Routes
import com.michael.nectargroceriesapp.ui.theme.NectarGroceriesAppTheme
import com.michael.nectargroceriesapp.utils.isFirstLaunch
import com.michael.nectargroceriesapp.utils.setFirstLaunchDone

@Composable
fun GroceriesApp(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val startDestination = if(isFirstLaunch(context))
        Routes.WelcomeScreen.route
    else
        Routes.HomeScreen.route

    LaunchedEffect(Unit) {
        setFirstLaunchDone(context)
    }

    NectarGroceriesAppTheme(
        dynamicColor = false
    ) {
        Scaffold (
            bottomBar = { BottomBar(navController) },
            modifier = modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            AppNavHost(
                navHostController = navController,
                startDestination = startDestination,
                modifier = Modifier.padding(innerPadding)
            )
        }

    }
}