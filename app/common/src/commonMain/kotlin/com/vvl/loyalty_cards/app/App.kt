package com.vvl.loyalty_cards.app

import androidx.compose.runtime.Composable
import com.vvl.loyalty_cards.app.theme.AppTheme
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.features.impl.home.view.HomeView
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.view.WidgetDetailsView
import com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.vvl.loyalty_cards.features.impl.root.view.RootView
import com.vvl.loyalty_cards.features.impl.widgets_list.view.WidgetsListView

@Composable
internal fun App(rootComponent: RootComponent) {
    AppTheme {
        // TODO: think about DI
        RootView(
            component = rootComponent,
            homeView = { component, animatedVisibilityScope ->
                HomeView(
                    component = component,
                    loyaltyCardsListView = { component ->
                        LoyaltyCardsListView(
                            component,
                            animatedVisibilityScope
                        )
                    },
                    widgetsListView = { component ->
                        WidgetsListView(
                            component,
                            animatedVisibilityScope
                        )
                    },
                    animatedVisibilityScope
                )
            },
            loyaltyCardDetailsView = { component, animatedVisibilityScope ->
                LoyaltyCardDetailsView(
                    component,
                    animatedVisibilityScope
                )
            },
            addLoyaltyCardView = { AddLoyaltyCardView(it) },
            widgetDetailsView = { component, animatedVisibilityScope ->
                WidgetDetailsView(component, animatedVisibilityScope)
            }
        )
    }
}