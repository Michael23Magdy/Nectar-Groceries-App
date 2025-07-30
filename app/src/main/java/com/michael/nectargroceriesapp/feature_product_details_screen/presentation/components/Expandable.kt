package com.michael.nectargroceriesapp.feature_product_details_screen.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Expandable(
    header: String,
    modifier: Modifier = Modifier,
    hint: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    var expanded by rememberSaveable{ mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 0f else -90f,
        label = "Rotation"
    )

    Column (modifier = modifier) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = header, style = MaterialTheme.typography.titleSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(!expanded) { hint() }
            Spacer(modifier = Modifier.width(3.dp))
            TextButton(onClick = { expanded = !expanded}) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotationAngle)
                )
            }
        }

        AnimatedVisibility(visible = expanded) {
            content()
        }
    }
}