package com.michael.nectargroceriesapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute(navController) == Screen.HomeScreen.route,
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentRoute(navController) == Screen.ExploreScreen.route,
            onClick = { navController.navigate(Screen.ExploreScreen.route) },
            icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
            label = { Text("Explore") }
        )
        NavigationBarItem(
            selected = currentRoute(navController) == Screen.CartScreen.route,
            onClick = { navController.navigate(Screen.CartScreen.route) },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
            label = { Text("Cart") }
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

