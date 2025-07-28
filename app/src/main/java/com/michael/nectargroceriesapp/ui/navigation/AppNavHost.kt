package com.michael.nectargroceriesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.michael.nectargroceriesapp.core.presentation.test.TestScreen
import com.michael.nectargroceriesapp.feature_home_screen.presentation.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route,
        modifier = modifier
    ){
        composable(Routes.WelcomeScreen.route) {
            TestScreen("welcome")
        }
        composable(Routes.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Routes.ProductScreen.route) {
            TestScreen("product")
        }
        composable(Routes.ExploreScreen.route) {
            TestScreen("Explore")
        }
        composable(Routes.CategoryScreen.route) {
            TestScreen("Category")
        }
        composable(Routes.SearchScreen.route) {
            TestScreen("Search")
        }
        composable(Routes.CartScreen.route) {
            TestScreen("Cart")
        }
        composable(Routes.OrderAcceptedScreen.route) {
            TestScreen("Order")
        }


    }
}