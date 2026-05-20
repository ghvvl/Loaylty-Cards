package com.vvl.loyalty_cards.app.base

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.vvl.loyalty_cards.app.App
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.common.icons.ArrowBack
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import platform.UIKit.UIViewController

class RootViewController(componentContext: ComponentContext) : KoinComponent {

    private val rootComponent: RootComponent by inject { parametersOf(componentContext) }

    @OptIn(ExperimentalSharedTransitionApi::class)
    fun getUIViewController(backDispatcher: BackDispatcher): UIViewController {
        return ComposeUIViewController {
            PredictiveBackGestureOverlay(
                modifier = Modifier.fillMaxSize(),
                backDispatcher = backDispatcher,
                backIcon = { progress, _ ->
                    PredictiveBackGestureIcon(
                        imageVector = ArrowBack,
                        progress = progress,
                    )
                }
            ) { App(rootComponent) }
        }
    }
}
