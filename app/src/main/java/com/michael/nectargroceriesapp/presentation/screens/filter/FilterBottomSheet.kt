package com.michael.nectargroceriesapp.presentation.screens.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.michael.nectargroceriesapp.R
import com.michael.nectargroceriesapp.domain.usecase.ProductFilterRule
import com.michael.nectargroceriesapp.domain.usecase.displayName
import com.michael.nectargroceriesapp.presentation.components.NectarButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    showBottomSheet: Boolean = false,
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState,
    onChange: (ProductFilterRule) -> Unit,
    viewModel: FilterBottomSheetViewModel = hiltViewModel()
) {
    val filterRules = viewModel.filterRules

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            FilterBottomSheetSection(
                stringResource(R.string.categories),
                filterRules = filterRules.filter { it.key is ProductFilterRule.Category },
                onRuleChange = { rule, isChecked ->
                    viewModel.updateFilterRule(rule, isChecked)
                }
            )
            FilterBottomSheetSection(
                stringResource(R.string.prices),
                filterRules = filterRules
                    .filter { it.key is ProductFilterRule.PriceRange }
                    .toSortedMap( compareBy { (it as ProductFilterRule.PriceRange).min } ),
                onRuleChange = { rule, isChecked ->
                    viewModel.updateFilterRule(rule, isChecked)
                }
            )
            FilterBottomSheetSection(
                stringResource(R.string.review),
                filterRules = filterRules
                    .filter { it.key is ProductFilterRule.Review }
                    .toSortedMap( compareBy { (it as ProductFilterRule.Review).review } ),
                onRuleChange = { rule, isChecked ->
                    viewModel.updateFilterRule(rule, isChecked)
                }
            )

            NectarButton(
                onClick = {
                    onChange(viewModel.getRule())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ) {
                Text(text = stringResource(R.string.apply_filter))
            }
        }
    }
}


@Composable
fun FilterBottomSheetSection(
    title: String,
    filterRules: Map<ProductFilterRule, Boolean>,
    onRuleChange: (ProductFilterRule, Boolean) -> Unit
) {
    if (filterRules.isEmpty()) return
    Column(modifier = Modifier.padding(16.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        filterRules.forEach { (rule, isSelected) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { onRuleChange(rule, it) },
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = rule.displayName(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
