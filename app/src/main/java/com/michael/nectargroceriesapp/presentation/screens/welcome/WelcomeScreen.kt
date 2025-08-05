package com.michael.nectargroceriesapp.presentation.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.presentation.components.NectarButton
import com.michael.nectargroceriesapp.ui.navigation.Routes

@Composable
fun WelcomeScreen(
    navHostController: NavHostController
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_onstart),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter =  painterResource(R.drawable.nectar_logo),
                contentDescription = null,
                Modifier.size(180.dp)
            )

            Text(
                text = "Welcome\nto our store",
                style = MaterialTheme.typography.displayMedium,
                fontSize = 45.sp,
                textAlign = TextAlign.Center,
                lineHeight = 60.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Ger your groceries in as fast as one hour",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray.copy(alpha = 0.7f),
            )
            Spacer(modifier = Modifier.height(50.dp))
            NectarButton(
                onClick = {
                    navHostController.popBackStack()
                    navHostController.navigate(Routes.HomeScreen.route)
                },
                modifier = Modifier.fillMaxWidth().height(68.dp)
            ) {
                Text(text = "Get Started", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars))
        }
    }
}
