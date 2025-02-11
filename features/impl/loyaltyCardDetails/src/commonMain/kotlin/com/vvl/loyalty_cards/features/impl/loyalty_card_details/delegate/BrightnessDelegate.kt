package com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate

import com.vvl.loyalty_cards.features.api.loyalty_card_details.model.BrightnessMode

interface BrightnessDelegate {
    fun setBrightness(brightnessMode: BrightnessMode)
}
