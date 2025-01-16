package com.vvl.loyalty_cards

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.vvl.loyalty_cards.api.root.component.RootComponent
import com.vvl.loyalty_cards.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import com.vvl.loyalty_cards.impl.loyalty_cards.storage.loyaltyCardsModule
import com.vvl.loyalty_cards.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.vvl.loyalty_cards.impl.root.view.RootView
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform

fun main() = application {
    KoinPlatform.startKoin(listOf(appModule, loyaltyCardsModule), Level.INFO)
    val lifecycle = LifecycleRegistry()
    val componentContext = DefaultComponentContext(lifecycle = lifecycle)
    val rootComponent: RootComponent = KoinPlatform.getKoin().get { parametersOf(componentContext) }

    Window(onCloseRequest = ::exitApplication, title = "Loyalty Cards") {
        AppTheme {
            RootView(
                component = rootComponent,
                loyaltyCardsListView = { LoyaltyCardsListView(it) },
                loyaltyCardDetailsView = { LoyaltyCardDetailsView(it) },
                addLoyaltyCardView = { AddLoyaltyCardView(it) }
            )
        }
    }
}