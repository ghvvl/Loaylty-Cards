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
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.impl.add_loyalty_card.component.AddLoyaltyCardComponentImpl
import com.vvl.loyalty_cards.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.impl.loyalty_card_details.component.LoyaltyCardDetailsComponentImpl
import com.vvl.loyalty_cards.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import com.vvl.loyalty_cards.impl.loyalty_cards_list.component.LoyaltyCardsListComponentImpl
import com.vvl.loyalty_cards.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.vvl.loyalty_cards.impl.root.component.RootComponentImpl
import com.vvl.loyalty_cards.impl.root.view.RootView
import org.koin.android.ext.android.get

internal class MainActivity : ComponentActivity() {

    private fun getLoyaltyCardsListComponent(
        context: ComponentContext,
        navigator: RootNavigator
    ): LoyaltyCardsListComponent =
        LoyaltyCardsListComponentImpl(
            componentContext = context,
            loyaltyCardsStorage = get(),
            rootNavigator = navigator
        )

    private fun getLoyaltyCardDetailsComponent(
        context: ComponentContext,
        navigator: RootNavigator
    ): LoyaltyCardDetailsComponent =
        LoyaltyCardDetailsComponentImpl(componentContext = context)

    private fun getAddLoyaltyCardComponent(
        context: ComponentContext,
        navigator: RootNavigator
    ): AddLoyaltyCardComponent =
        AddLoyaltyCardComponentImpl(
            componentContext = context,
            rootNavigator = navigator
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(
            componentContext = defaultComponentContext(),
            loyaltyCardsListComponent = ::getLoyaltyCardsListComponent,
            loyaltyCardDetailsComponent = ::getLoyaltyCardDetailsComponent,
            addLoyaltyCardComponent = ::getAddLoyaltyCardComponent
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