package com.vvl.loyalty_cards

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
actual fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        /*colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme,*/
        content = content
    )
}