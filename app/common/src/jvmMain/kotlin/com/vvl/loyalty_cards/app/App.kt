package com.vvl.loyalty_cards.app

import androidx.compose.runtime.Composable
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import org.koin.core.context.GlobalContext.get

@Composable
fun App() {
    val rootComponent: RootComponent = get()
    App(rootComponent)
}