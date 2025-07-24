package com.michael.nectargroceriesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.michael.nectargroceriesapp.core.presentation.test.TestScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = modifier
    ){
        composable(Screen.WelcomeScreen.route) {
            TestScreen("welcome")
        }
        composable(Screen.HomeScreen.route) {
            TestScreen("home")
        }
        composable(Screen.ProductScreen.route) {
            TestScreen("product")
        }
        composable(Screen.ExploreScreen.route) {
            TestScreen("Explore")
        }
        composable(Screen.CategoryScreen.route) {
            TestScreen("Category")
        }
        composable(Screen.SearchScreen.route) {
            TestScreen("Search")
        }
        composable(Screen.CartScreen.route) {
            TestScreen("Cart")
        }
        composable(Screen.OrderAcceptedScreen.route) {
            TestScreen("Order")
        }


    }
}