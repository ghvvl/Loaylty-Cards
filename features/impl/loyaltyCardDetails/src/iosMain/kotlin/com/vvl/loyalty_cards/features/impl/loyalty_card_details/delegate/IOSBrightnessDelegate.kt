package com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate

import com.vvl.loyalty_cards.features.api.loyalty_card_details.model.BrightnessMode
import platform.UIKit.UIScreen

internal class IOSBrightnessDelegate : BrightnessDelegate {

    private var userBrightness = -1.0

    override fun setBrightness(brightnessMode: BrightnessMode) {
        if (userBrightness == -1.0) {
            userBrightness = UIScreen.mainScreen.brightness
        }
        UIScreen.mainScreen.setBrightness(
            if (brightnessMode == BrightnessMode.MAX) 1.0 else userBrightness
        )
    }
}
