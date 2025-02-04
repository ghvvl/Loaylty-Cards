package com.vvl.loyalty_cards.features.impl.add_loyalty_card.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils.BarcodeAnalyzer
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils.BarcodeScannerPreviewView
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils.OrientationListener
import platform.UIKit.UIView

@Composable
actual fun PlatformCameraPreviewView(component: AddLoyaltyCardComponent) {
    val scope = rememberCoroutineScope()
    val analyzer = remember { BarcodeAnalyzer(scope, component::onCodeReceived) }
    DisposableEffect(Unit) {
        val listener = OrientationListener { orientation ->
            analyzer.setCurrentOrientation(orientation)
        }

        listener.register()

        onDispose { listener.unregister() }
    }

    UIKitView<UIView>(
        modifier = Modifier.fillMaxSize(),
        factory = { BarcodeScannerPreviewView(analyzer).apply { analyzer.prepare(layer) } },
        properties = UIKitInteropProperties(
            isInteractive = true,
            isNativeAccessibilityEnabled = true
        )
    )
}