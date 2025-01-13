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
import com.vvl.loyalty_cards.impl.add_loyalty_card.component.AddLoyaltyCardComponentImpl
import com.vvl.loyalty_cards.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.impl.loyalty_card_details.component.LoyaltyCardDetailsComponentImpl
import com.vvl.loyalty_cards.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import com.vvl.loyalty_cards.impl.loyalty_cards_list.component.LoyaltyCardsListComponentImpl
import com.vvl.loyalty_cards.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.vvl.loyalty_cards.impl.root.component.RootComponentImpl
import com.vvl.loyalty_cards.impl.root.view.RootView

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(
            componentContext = defaultComponentContext(),
            loyaltyCardsListComponent = { LoyaltyCardsListComponentImpl(it) },
            loyaltyCardDetailsComponent = { LoyaltyCardDetailsComponentImpl(it) },
            addLoyaltyCardComponent = { AddLoyaltyCardComponentImpl(it) }
        )

        setContent {
            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme()) {
                    dynamicDarkColorScheme(LocalContext.current)
                } else {
                    dynamicLightColorScheme(LocalContext.current)
                }
            ) {
                RootView(
                    component = rootComponent,
                    loyaltyCardsListView = { LoyaltyCardsListView(it) },
                    loyaltyCardDetailsView = { LoyaltyCardDetailsView(it) },
                    addLoyaltyCardView = { AddLoyaltyCardView(it) }
                )
            }
        }
    }
}