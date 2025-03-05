package com.vvl.loyalty_cards.app.base

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.vvl.loyalty_cards.features.impl.root.view.RootView
import com.vvl.loyalty_cards.app.theme.AppTheme
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import platform.UIKit.UIViewController

class RootViewController(componentContext: ComponentContext) : KoinComponent {

    private val rootComponent: RootComponent by inject { parametersOf(componentContext) }

    @OptIn(ExperimentalSharedTransitionApi::class)
    fun getUIViewController(backDispatcher: BackDispatcher): UIViewController {
        return ComposeUIViewController(
            configure = { enforceStrictPlistSanityCheck = false },
            content = {
                PredictiveBackGestureOverlay(
                    modifier = Modifier.fillMaxSize(),
                    backDispatcher = backDispatcher,
                    backIcon = { progress, _ ->
                        PredictiveBackGestureIcon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            progress = progress,
                        )
                    }
                ) {
                    AppTheme {
                        // TODO: think about DI
                        RootView(
                            component = rootComponent,
                            loyaltyCardsListView = { component, animatedVisibilityScope ->
                                LoyaltyCardsListView(
                                    component,
                                    animatedVisibilityScope
                                )
                            },
                            loyaltyCardDetailsView = { component, animatedVisibilityScope ->
                                LoyaltyCardDetailsView(
                                    component,
                                    animatedVisibilityScope
                                )
                            },
                            addLoyaltyCardView = { AddLoyaltyCardView(it) }
                        )
                    }
                }
            }
        )
    }
}
