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
import com.michael.nectargroceriesapp.presentation.screens.cart.CartScreen
import com.michael.nectargroceriesapp.presentation.screens.test.TestScreen
import com.michael.nectargroceriesapp.presentation.screens.category.CategoryScreenRoot
import com.michael.nectargroceriesapp.presentation.screens.category.CategoryViewModel
import com.michael.nectargroceriesapp.presentation.screens.explore.ExploreScreen
import com.michael.nectargroceriesapp.presentation.screens.home.HomeScreen
import com.michael.nectargroceriesapp.presentation.screens.product_details.ProductDetailsScreenRoot
import com.michael.nectargroceriesapp.presentation.screens.product_details.ProductDetailsViewModel
import com.michael.nectargroceriesapp.presentation.screens.search.SearchScreen
import com.michael.nectargroceriesapp.presentation.screens.search.SearchViewModel

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
            ProductDetailsScreenRoot(navController, viewModel)
        }
        composable(Routes.ExploreScreen.route) {
            ExploreScreen(navController)
        }
        composable(
            Routes.CategoryScreen.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) {
            val viewModel: CategoryViewModel = hiltViewModel()
            CategoryScreenRoot(navController, viewModel)
        }
        composable(
            Routes.SearchScreen.route,
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(navController, viewModel)
        }
        composable(Routes.CartScreen.route) {
            CartScreen()
        }
        composable(Routes.OrderAcceptedScreen.route) {
            TestScreen("Order")
        }


    }
}