package com.michael.nectargroceriesapp.presentation.screens.product_details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.presentation.components.OutlinedNectarButton
import com.michael.nectargroceriesapp.presentation.components.TextNectarButton

@Composable
fun NumberSelector(
    number: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextNectarButton(onClick = onDecrease){
            Icon(painter = painterResource(R.drawable.minus), contentDescription = null, tint = Color(0xFFB3B3B3))
        }
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedNectarButton(onClick = onIncrease) {
            Text(text = number.toString())
        }
        Spacer(modifier = Modifier.width(5.dp))
        TextNectarButton(onClick = onIncrease) {
            Icon(painter = painterResource(R.drawable.add), contentDescription = null)
        }
    }
}