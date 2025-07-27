package com.michael.nectargroceriesapp.ui.navigation

import android.util.Log
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
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.michael.nectargroceriesapp.ui.theme.NectarGroceriesAppTheme

@Composable
fun BottomBar(navController: NavHostController) {
    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
        indicatorColor = Color(0x00000000)
    )
    Box(
        contentAlignment = androidx.compose.ui.Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                clip = false
            )
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)), // clip children
    ) {
        NavigationBar() {
            NavigationBarItem(
                selected = currentRoute(navController) == Routes.HomeRoutes.route,
                onClick = { navController.navigate(Routes.HomeRoutes.route) },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                colors = colors
            )
            NavigationBarItem(
                selected = currentRoute(navController) == Routes.ExploreRoutes.route,
                onClick = { navController.navigate(Routes.ExploreRoutes.route) },
                icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
                label = { Text("Explore") },
                colors = colors
            )
            NavigationBarItem(
                selected = currentRoute(navController) == Routes.CartRoutes.route,
                onClick = { navController.navigate(Routes.CartRoutes.route) },
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                label = { Text("Cart") },
                colors = colors
            )
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar(){
    BottomBar(navController = NavHostController(LocalContext.current))
}