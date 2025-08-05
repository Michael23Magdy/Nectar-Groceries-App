package com.michael.nectargroceriesapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.R

@Composable
fun BackButton(navController: NavHostController) {
    TextButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.rounded_keyboard_arrow_left_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}