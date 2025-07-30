package com.michael.nectargroceriesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.michael.nectargroceriesapp.core.presentation.test.TestScreen
import com.michael.nectargroceriesapp.feature_home_screen.presentation.HomeScreen
import com.michael.nectargroceriesapp.feature_product_details_screen.presentation.ProductDetailsScreen
import com.michael.nectargroceriesapp.feature_product_details_screen.presentation.ProductDetailsViewModel

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
        composable(
            Routes.ProductScreen.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) {
            val viewModel: ProductDetailsViewModel = hiltViewModel()
            ProductDetailsScreen(navController, viewModel)
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