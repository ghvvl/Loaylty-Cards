package com.vvl.loyalty_cards.features.impl.add_loyalty_card.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnResume
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class AddLoyaltyCardComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val loyaltyCardsStorage: LoyaltyCardsStorage
) : AddLoyaltyCardComponent, ComponentContext by componentContext {

    override val useExternalBarcodeScanner = MutableStateFlow(false)

    private val _requestPermission = Channel<Unit>(1, BufferOverflow.DROP_OLDEST)

    override val requestPermission = _requestPermission.receiveAsFlow()

    override val wasPermissionGranted = MutableStateFlow(false)

    init {
        lifecycle.doOnResume { _requestPermission.trySend(Unit) }
    }

    override fun onPermissionResultReceived(isGranted: Boolean) {
        wasPermissionGranted.tryEmit(isGranted)
    }

    override fun onCodeReceived(code: String, codeType: LoyaltyCardCodeType) {
        val loyaltyCard = LoyaltyCard(code, codeType)
        coroutineScope().launch {
            loyaltyCardsStorage.addLoyaltyCard(loyaltyCard)
            rootNavigator.openLoyaltyCardDetails(loyaltyCard, true)
        }
    }

    override fun onBackClicked() {
        rootNavigator.onBackClicked()
    }
}
