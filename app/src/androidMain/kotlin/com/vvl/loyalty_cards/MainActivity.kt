package com.vvl.loyalty_cards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.vvl.loyalty_cards.api.root.component.RootComponent
import com.vvl.loyalty_cards.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import com.vvl.loyalty_cards.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.vvl.loyalty_cards.impl.root.view.RootView
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val rootComponent: RootComponent = get { parametersOf(defaultComponentContext()) }
        setContent {
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
}