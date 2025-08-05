package com.michael.nectargroceriesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.michael.nectargroceriesapp.ui.navigation.Routes
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.presentation.components.NectarButton

@Composable
fun OrderAcceptedScreen(
    navHostController: NavHostController
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.checkout_background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp).align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(R.drawable.accepted_order_image),
                contentDescription = null,
                modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Your Order has been\n accepted",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displayMedium,
                fontSize = 34.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Your items has been placed and is on \n" +
                    "it’s way to being processed",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }

        Column (
            modifier = Modifier.padding(20.dp).align(Alignment.BottomCenter),

        ){
            NectarButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(60.dp),
            ) {
                Text(text = "Track Order")
            }
            TextButton(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                onClick = { navHostController.navigate(Routes.HomeScreen.route) }
            ) {
                Text(text = "Back to Home")
            }
        }
    }

}