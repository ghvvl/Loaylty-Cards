package com.vvl.loyalty_cards.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.com.vvl.loyalty_cards.features.impl.root.view.RootView
import com.vvl.loyalty_cards.app.theme.AppTheme
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.di.androidLoyaltyCardDetailsModule
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import org.koin.android.ext.android.get
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

internal class MainActivity : ComponentActivity() {

    private val modules = listOf(
        androidLoyaltyCardDetailsModule(this)
    )

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        loadKoinModules(modules)

        val rootComponent: RootComponent = get { parametersOf(defaultComponentContext()) }
        setContent {
            AppTheme {
                // TODO: think about DI
                RootView(
                    component = rootComponent,
                    loyaltyCardsListView = { component, isSharedElementVisible ->
                        LoyaltyCardsListView(
                            component,
                            isSharedElementVisible
                        )
                    },
                    loyaltyCardDetailsView = { component, isSharedElementVisible ->
                        LoyaltyCardDetailsView(
                            component,
                            isSharedElementVisible
                        )
                    },
                    addLoyaltyCardView = { AddLoyaltyCardView(it) }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(modules)
    }
}
