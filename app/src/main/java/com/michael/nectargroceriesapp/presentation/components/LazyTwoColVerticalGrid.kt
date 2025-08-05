package com.michael.nectargroceriesapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michael.nectargroceriesapp.ui.theme.Dimen

@Composable
fun LazyTwoColVerticalGrid(
    modifier: Modifier = Modifier,
    content: LazyGridScope.() -> Unit = {}
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier.fillMaxWidth().padding(Dimen.paddingLarge, Dimen.paddingLarge, Dimen.paddingLarge, 0.dp),
    ) {
        content()
    }
}