package com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate

import android.app.Activity
import android.view.WindowManager
import com.vvl.loyalty_cards.features.api.loyalty_card_details.model.BrightnessMode

internal class AndroidBrightnessDelegate(
    private val activity: Activity
) : BrightnessDelegate {

    override fun setBrightness(brightnessMode: BrightnessMode) {
        val brightness = when (brightnessMode) {
            BrightnessMode.MAX -> 1f
            BrightnessMode.AUTO -> WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
        }

        val layoutParams: WindowManager.LayoutParams = activity.window.attributes
        layoutParams.screenBrightness = brightness
        activity.window.attributes = layoutParams
    }
}
