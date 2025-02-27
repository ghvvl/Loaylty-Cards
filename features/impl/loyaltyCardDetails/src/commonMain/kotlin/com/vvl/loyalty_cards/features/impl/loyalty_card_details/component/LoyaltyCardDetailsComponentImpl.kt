package com.vvl.loyalty_cards.features.impl.loyalty_card_details.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.arkivanov.decompose.value.updateAndGet
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnPause
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.loyalty_card_details.model.BrightnessMode
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate.BrightnessDelegate
import kotlinx.coroutines.launch

internal class LoyaltyCardDetailsComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val providedLoyaltyCard: LoyaltyCard,
    private val loyaltyCardsStorage: LoyaltyCardsStorage,
    private val brightnessDelegate: BrightnessDelegate,
    private val widgetDelegate: WidgetDelegate
) : LoyaltyCardDetailsComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    override val loyaltyCard = MutableValue(providedLoyaltyCard)

    override val brightnessMode = MutableValue(BrightnessMode.AUTO)

    init {
        brightnessMode.subscribe(brightnessDelegate::setBrightness)
        lifecycle.doOnPause { brightnessMode.value = BrightnessMode.AUTO }
    }

    override fun onResetClicked() {
        loyaltyCard.value = providedLoyaltyCard
        coroutineScope.launch {
            loyaltyCardsStorage.updateLoyaltyCard(providedLoyaltyCard)
            widgetDelegate.updateAllWidgets()
        }
    }

    override fun onBrightnessClicked() {
        brightnessMode.update {
            when (it) {
                BrightnessMode.AUTO -> BrightnessMode.MAX
                BrightnessMode.MAX -> BrightnessMode.AUTO
            }
        }
    }

    override fun onLoyaltyCardNameChanged(name: String) {
        val newLoyaltyCard = loyaltyCard.updateAndGet { it.copy(name = name) }
        coroutineScope.launch {
            loyaltyCardsStorage.updateLoyaltyCard(newLoyaltyCard)
            widgetDelegate.updateAllWidgets()
        }
    }

    override fun onBackClicked() = rootNavigator.onBackClicked()
}
