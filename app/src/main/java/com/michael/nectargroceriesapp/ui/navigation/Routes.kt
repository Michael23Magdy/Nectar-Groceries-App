package com.michael.nectargroceriesapp.ui.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen("welcome")

    object HomeScreen : Screen("home")
    object ProductScreen : Screen("product")

    object ExploreScreen : Screen("explore")
    object CategoryScreen : Screen("category")

    object SearchScreen : Screen("search")

    object CartScreen : Screen("cart")
    object OrderAcceptedScreen : Screen("order_accepted")
}