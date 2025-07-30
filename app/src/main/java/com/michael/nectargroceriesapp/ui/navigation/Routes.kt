package com.michael.nectargroceriesapp.ui.navigation

sealed class Routes(val route: String) {
    object WelcomeScreen : Routes("welcome")

    object HomeScreen : Routes("home")
    object ProductScreen : Routes("product/{productId}") {
        fun createRoute(productId: Int) = "product/$productId?ts=${System.currentTimeMillis()}"
    }

    object ExploreScreen : Routes("explore")
    object CategoryScreen : Routes("category/{categoryId}") {
        fun createRoute(categoryId: String) = "category/$categoryId?ts=${System.currentTimeMillis()}"
    }

    object SearchScreen : Routes("search")

    object CartScreen : Routes("cart")
    object OrderAcceptedScreen : Routes("order_accepted")
}