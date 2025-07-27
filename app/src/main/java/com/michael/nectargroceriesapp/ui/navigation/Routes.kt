package com.michael.nectargroceriesapp.ui.navigation

sealed class Routes(val route: String) {
    object WelcomeRoutes : Routes("welcome")

    object HomeRoutes : Routes("home")
    object ProductRoutes : Routes("product/{productId}") {
        fun createRoute(productId: Int) = "product/$productId"
    }

    object ExploreRoutes : Routes("explore")
    object CategoryRoutes : Routes("category/{categoryId}") {
        fun createRoute(categoryId: String) = "category/$categoryId"
    }

    object SearchRoutes : Routes("search")

    object CartRoutes : Routes("cart")
    object OrderAcceptedRoutes : Routes("order_accepted")
}