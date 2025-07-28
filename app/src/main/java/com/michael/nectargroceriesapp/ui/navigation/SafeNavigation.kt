package com.michael.nectargroceriesapp.ui.navigation

import androidx.navigation.NavHostController

fun NavHostController.safeNavigateSingleTopTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(this@safeNavigateSingleTopTo.graph.startDestinationId) {
            saveState = true
        }
        restoreState = true
    }
}
