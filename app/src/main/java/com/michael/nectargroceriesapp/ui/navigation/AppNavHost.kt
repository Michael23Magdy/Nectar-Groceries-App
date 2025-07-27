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
        startDestination = Routes.HomeRoutes.route,
        modifier = modifier
    ){
        composable(Routes.WelcomeRoutes.route) {
            TestScreen("welcome")
        }
        composable(Routes.HomeRoutes.route) {
            TestScreen("home")
        }
        composable(Routes.ProductRoutes.route) {
            TestScreen("product")
        }
        composable(Routes.ExploreRoutes.route) {
            TestScreen("Explore")
        }
        composable(Routes.CategoryRoutes.route) {
            TestScreen("Category")
        }
        composable(Routes.SearchRoutes.route) {
            TestScreen("Search")
        }
        composable(Routes.CartRoutes.route) {
            TestScreen("Cart")
        }
        composable(Routes.OrderAcceptedRoutes.route) {
            TestScreen("Order")
        }


    }
}