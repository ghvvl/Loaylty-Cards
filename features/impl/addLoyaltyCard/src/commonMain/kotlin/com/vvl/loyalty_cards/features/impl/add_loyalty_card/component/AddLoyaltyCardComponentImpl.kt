package com.vvl.loyalty_cards.features.impl.add_loyalty_card.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnResume
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils.generateColor
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
internal class AddLoyaltyCardComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val loyaltyCardsStorage: LoyaltyCardsStorage,
    private val widgetDelegate: WidgetDelegate
) : AddLoyaltyCardComponent, ComponentContext by componentContext {

    override val useExternalBarcodeScanner = MutableStateFlow(false)

    private val _requestPermission = Channel<Unit>(1, BufferOverflow.DROP_OLDEST)

    override val requestPermission = _requestPermission.receiveAsFlow()

    override val wasPermissionGranted = MutableStateFlow(false)

    init {
        // lifecycle.doOnResume { _requestPermission.trySend(Unit) }
        onCodeReceived(Uuid.random().toString(), LoyaltyCardCodeType.QR_CODE)
    }

    override fun onPermissionResultReceived(isGranted: Boolean) {
        wasPermissionGranted.tryEmit(isGranted)
    }

    override fun onCodeReceived(code: String, codeType: LoyaltyCardCodeType) {
        val color = generateColor()
        val loyaltyCard = LoyaltyCard("", code, codeType, color)
        coroutineScope().launch {
            loyaltyCardsStorage.addLoyaltyCard(loyaltyCard)
            widgetDelegate.updateAllWidgets()
            rootNavigator.openLoyaltyCardDetails(loyaltyCard, true)
        }
    }

    override fun onBackClicked() {
        rootNavigator.onBackClicked()
    }
}
