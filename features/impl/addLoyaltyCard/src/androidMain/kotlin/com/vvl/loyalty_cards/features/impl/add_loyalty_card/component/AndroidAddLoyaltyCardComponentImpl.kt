package com.vvl.loyalty_cards.features.impl.add_loyalty_card.component

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils.mapToLoyaltyCardCodeType
import kotlinx.coroutines.flow.MutableStateFlow

internal class AndroidAddLoyaltyCardComponentImpl(
    context: Context,
    private val delegatedComponent: AddLoyaltyCardComponent
) : AddLoyaltyCardComponent by delegatedComponent {

    override val useExternalBarcodeScanner = MutableStateFlow(true)

    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .enableAutoZoom()
        .build()

    init {
        GmsBarcodeScanning.getClient(context, options)
            .startScan()
            .addOnSuccessListener {
                onCodeReceived(it.rawValue.orEmpty(), it.format.mapToLoyaltyCardCodeType())
            }
            .addOnFailureListener { useExternalBarcodeScanner.tryEmit(false) }
            .addOnCanceledListener { delegatedComponent.onBackClicked() }
            .addOnFailureListener { delegatedComponent.onBackClicked() }
    }
}
