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
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier,
    invertBoarder: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(invertBoarder) {
            OutlinedNectarButton(onClick = onDecrease, modifier = modifier){
                Icon(painter = painterResource(R.drawable.minus), contentDescription = null, tint = Color(0xFFB3B3B3))
            }
            Spacer(modifier = Modifier.width(5.dp))
            TextNectarButton(onClick = onIncrease, modifier = modifier) {
                Text(text = number.toString())
            }
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedNectarButton(onClick = onIncrease, modifier = modifier) {
                Icon(painter = painterResource(R.drawable.add), contentDescription = null)
            }
        } else {
            TextNectarButton(onClick = onDecrease, modifier = modifier){
                Icon(painter = painterResource(R.drawable.minus), contentDescription = null, tint = Color(0xFFB3B3B3))
            }
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedNectarButton(onClick = onIncrease, modifier = modifier) {
                Text(text = number.toString())
            }
            Spacer(modifier = Modifier.width(5.dp))
            TextNectarButton(onClick = onIncrease, modifier = modifier) {
                Icon(painter = painterResource(R.drawable.add), contentDescription = null)
            }
        }

    }
}