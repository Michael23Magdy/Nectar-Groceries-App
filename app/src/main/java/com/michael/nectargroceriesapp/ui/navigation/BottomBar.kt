package com.michael.nectargroceriesapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
    Box(
        contentAlignment = androidx.compose.ui.Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                clip = false
            )
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)), // clip children
    ) {
        NavigationBar(
            tonalElevation = 4.dp,
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            NavigationBarItem(
                selected = currentRoute(navController) == Routes.HomeRoutes.route,
                onClick = { navController.navigate(Routes.HomeRoutes.route) },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") }
            )
            NavigationBarItem(
                selected = currentRoute(navController) == Routes.ExploreRoutes.route,
                onClick = { navController.navigate(Routes.ExploreRoutes.route) },
                icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
                label = { Text("Explore") }
            )
            NavigationBarItem(
                selected = currentRoute(navController) == Routes.CartRoutes.route,
                onClick = { navController.navigate(Routes.CartRoutes.route) },
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                label = { Text("Cart") }
            )
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

