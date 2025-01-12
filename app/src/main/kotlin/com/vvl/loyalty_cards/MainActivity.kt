package com.vvl.loyalty_cards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.platform.LocalContext
import com.arkivanov.decompose.defaultComponentContext
import com.vvl.loyalty_cards.impl.root.component.RootComponentImpl
import com.vvl.loyalty_cards.impl.root.view.RootView

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(componentContext = defaultComponentContext())

        setContent {
            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme()) {
                    dynamicDarkColorScheme(LocalContext.current)
                } else {
                    dynamicLightColorScheme(LocalContext.current)
                }
            ) {
                RootView(rootComponent)
            }
        }
    }
}