package com.vvl.loyalty_cards.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.vvl.loyalty_cards.app.theme.AppTheme
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksHandler
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.view.AddLoyaltyCardView
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.di.androidLoyaltyCardDetailsModule
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.view.LoyaltyCardDetailsView
import com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view.LoyaltyCardsListView
import com.vvl.loyalty_cards.features.impl.root.view.RootView
import com.vvl.loyalty_cards.features.impl.widget.widget.WIDGET_KEY_NAME
import dev.theolm.rinku.Rinku
import dev.theolm.rinku.RinkuInit
import dev.theolm.rinku.models.DeepLinkFilter
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

internal class MainActivity : ComponentActivity() {

    private val deepLinksHandler: DeepLinksHandler by inject()

    private val modules = listOf(
        androidLoyaltyCardDetailsModule(this)
    )

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        loadKoinModules(modules)

        RinkuInit(
            deepLinkFilter = object : DeepLinkFilter {
                override fun isValid(url: String): Boolean = deepLinksHandler.validateDeeplink(url)
            }
        )

        intent.extras?.getString(WIDGET_KEY_NAME)?.let(Rinku::handleDeepLink)
        addOnNewIntentListener {
            it.extras?.getString(WIDGET_KEY_NAME)?.let(Rinku::handleDeepLink)
        }

        val rootComponent: RootComponent = get { parametersOf(defaultComponentContext()) }
        setContent {
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

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(modules)
    }
}
