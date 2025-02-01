package com.vvl.loyalty_cards.impl.add_loyalty_card.utils

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType

internal class BarcodeAnalyzer(
    private val onCodeReceived: (code: String, codeType: LoyaltyCardCodeType) -> Unit
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @OptIn(ExperimentalGetImage::class)
    @Suppress("UnsafeCallOnNullableType")
    override fun analyze(imageProxy: ImageProxy) {
        scanner
            .process(
                InputImage.fromMediaImage(
                    imageProxy.image!!, imageProxy.imageInfo.rotationDegrees
                )
            )
            .addOnSuccessListener { barcodes ->
                if (barcodes.isNotEmpty()) {
                    with(barcodes.first()) {
                        onCodeReceived(rawValue.orEmpty(), format.mapToLoyaltyCardCodeType())
                    }
                }
            }
            .addOnCompleteListener { imageProxy.close() }
    }
}

@Suppress("CyclomaticComplexMethod")
internal fun Int.mapToLoyaltyCardCodeType(): LoyaltyCardCodeType = when (this) {
    Barcode.FORMAT_CODE_128 -> LoyaltyCardCodeType.CODE_128
    Barcode.FORMAT_CODE_39 -> LoyaltyCardCodeType.CODE_39
    Barcode.FORMAT_CODE_93 -> LoyaltyCardCodeType.CODE_93
    Barcode.FORMAT_CODABAR -> LoyaltyCardCodeType.CODABAR
    Barcode.FORMAT_DATA_MATRIX -> LoyaltyCardCodeType.DATA_MATRIX
    Barcode.FORMAT_EAN_13 -> LoyaltyCardCodeType.EAN_13
    Barcode.FORMAT_EAN_8 -> LoyaltyCardCodeType.EAN_8
    Barcode.FORMAT_ITF -> LoyaltyCardCodeType.ITF
    Barcode.FORMAT_QR_CODE -> LoyaltyCardCodeType.QR_CODE
    Barcode.FORMAT_UPC_A -> LoyaltyCardCodeType.UPC_A
    Barcode.FORMAT_UPC_E -> LoyaltyCardCodeType.UPC_E
    Barcode.FORMAT_PDF417 -> LoyaltyCardCodeType.PDF_417
    Barcode.FORMAT_AZTEC -> LoyaltyCardCodeType.AZTEC
    else -> throw IllegalArgumentException("barcode with type $this is not supported!")
}