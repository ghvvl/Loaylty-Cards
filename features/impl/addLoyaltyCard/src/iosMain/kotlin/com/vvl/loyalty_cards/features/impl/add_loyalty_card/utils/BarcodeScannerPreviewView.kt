package com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import platform.CoreGraphics.CGRectZero
import platform.QuartzCore.CATransaction
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
internal class BarcodeScannerPreviewView(
    private val analyzer: BarcodeAnalyzer
) : UIView(frame = cValue { CGRectZero }) {

    @OptIn(ExperimentalForeignApi::class)
    override fun layoutSubviews() {
        super.layoutSubviews()

        CATransaction.begin()
        CATransaction.setDisableActions(true)
        layer.setFrame(frame)
        analyzer.setFrame(frame)
        CATransaction.commit()
    }
}