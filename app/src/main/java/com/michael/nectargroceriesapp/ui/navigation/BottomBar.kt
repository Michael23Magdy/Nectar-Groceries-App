package com.michael.nectargroceriesapp.ui.navigation

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
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.michael.nectargroceriesapp.R

@Composable
fun BottomBar(navController: NavHostController) {
    when(currentRoute(navController)) {
        in listOf(Routes.HomeScreen.route, Routes.ExploreScreen.route, Routes.CartScreen.route) -> {
            BottomNavigationBar(navController)
        }
        else -> {}
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController) {
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
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background
        ) {
            bottomNavItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute(navController) == item.route,
                    onClick = { navController.safeNavigateSingleTopTo(item.route) },
                    icon = { Icon(painter = painterResource(item.icon), contentDescription = item.label) },
                    label = { Text(item.label) },
                    colors = colors
                )
            }
        }
    }

}

private data class BottomNavItem(
    val route: String,
    val icon: Int,
    val label: String
)
private val bottomNavItems = listOf(
    BottomNavItem(
        route = Routes.HomeScreen.route,
        icon = R.drawable.shop,
        label = "Shop"
    ),
    BottomNavItem(
        route = Routes.ExploreScreen.route,
        icon = R.drawable.explore,
        label = "Explore"
    ),
    BottomNavItem(
        route = Routes.CartScreen.route,
        icon = R.drawable.cart,
        label = "Cart"
    )
)


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