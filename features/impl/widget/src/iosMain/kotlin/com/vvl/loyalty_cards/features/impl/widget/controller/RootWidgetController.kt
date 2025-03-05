package com.vvl.loyalty_cards.features.impl.widget.controller

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.impl.widget.delegate.IOSWidgetDelegateImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RootWidgetController() : KoinComponent {

    private val storage: LoyaltyCardsStorage by inject()
    private val widgetDelegate: IOSWidgetDelegateImpl by inject()
    private val scope: CoroutineScope by inject()

    private val cards = MutableStateFlow<List<LoyaltyCard>>(emptyList())

    init {
        storage.loyaltyCards.onEach { cards.emit(it) }.launchIn(scope)
    }

    fun pickLoyaltyCards() = cards.value

    fun setCallback(callback: () -> Unit) {
        println("KEK wtf $callback")
        widgetDelegate.callback = callback
    }
}