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
import com.michael.nectargroceriesapp.presentation.screens.order_accepted.OrderAcceptedScreen
import com.michael.nectargroceriesapp.presentation.screens.cart.CartScreen
import com.michael.nectargroceriesapp.presentation.screens.category.CategoryScreenRoot
import com.michael.nectargroceriesapp.presentation.screens.category.CategoryViewModel
import com.michael.nectargroceriesapp.presentation.screens.explore.ExploreScreen
import com.michael.nectargroceriesapp.presentation.screens.home.HomeScreen
import com.michael.nectargroceriesapp.presentation.screens.product_details.ProductDetailsScreenRoot
import com.michael.nectargroceriesapp.presentation.screens.product_details.ProductDetailsViewModel
import com.michael.nectargroceriesapp.presentation.screens.search.SearchScreen
import com.michael.nectargroceriesapp.presentation.screens.search.SearchViewModel
import com.michael.nectargroceriesapp.presentation.screens.welcome.WelcomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ){
        composable(Routes.WelcomeScreen.route) {
            WelcomeScreen(navHostController)
        }
        composable(Routes.HomeScreen.route) {
            HomeScreen(navHostController, modifier)
        }
        composable(
            Routes.ProductScreen.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) {
            val viewModel: ProductDetailsViewModel = hiltViewModel()
            ProductDetailsScreenRoot(navHostController, modifier, viewModel)
        }
        composable(Routes.ExploreScreen.route) {
            ExploreScreen(navHostController, modifier)
        }
        composable(
            Routes.CategoryScreen.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) {
            val viewModel: CategoryViewModel = hiltViewModel()
            CategoryScreenRoot(navHostController, modifier, viewModel)
        }
        composable(
            Routes.SearchScreen.route,
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(navHostController, modifier, viewModel)
        }
        composable(Routes.CartScreen.route) {
            CartScreen(navHostController, modifier)
        }
        composable(Routes.OrderAcceptedScreen.route) {
            OrderAcceptedScreen(navHostController)
        }


    }
}