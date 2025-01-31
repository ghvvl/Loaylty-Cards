package com.vvl.loyalty_cards.api.add_loyalty_card.component

import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AddLoyaltyCardComponent {

    val useExternalBarcodeScanner: StateFlow<Boolean>

    val requestPermission: Flow<Unit>

    val wasPermissionGranted: StateFlow<Boolean>

    fun onPermissionResultReceived(isGranted: Boolean)

    fun onCodeReceived(code: String, codeType: LoyaltyCardCodeType)

    fun onBackClicked()
}