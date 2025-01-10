package com.vvl.loyalty_cards.impl.widget

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

fun String.encodeToBitmap(
    width: Int,
    height: Int,
    backgroundColor: Int,
    accentColor: Int
): Bitmap =
    MultiFormatWriter()
        .encode(this, BarcodeFormat.CODE_128, width, height)
        .toBitmap(backgroundColor, accentColor)

private fun BitMatrix.toBitmap(
    backgroundColor: Int,
    accentColor: Int
): Bitmap {
    val pixels = IntArray(width * height)

    repeat(height) { y ->
        val offset = y * width
        repeat(width) { x ->
            pixels[offset + x] = if (get(x, y)) backgroundColor else accentColor
        }
    }

    return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        .apply {

            setPixels(pixels, 0, width, 0, 0, width, height)
        }
}


