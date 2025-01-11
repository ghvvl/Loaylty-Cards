package com.vvl.loyalty_cards.impl.widget

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

fun String.encodeToBitmaps(
    width: Int,
    height: Int
): Pair<Bitmap, Bitmap> =
    MultiFormatWriter()
        .encode(this, BarcodeFormat.CODE_128, width, height)
        .toBitmaps()

private fun BitMatrix.toBitmaps(): Pair<Bitmap, Bitmap> {
    val backPixels = IntArray(width * height)
    val frontPixels = IntArray(width * height)

    repeat(height) { y ->
        val offset = y * width
        repeat(width) { x ->
            if (get(x, y)) {
                backPixels[offset + x] = Color.WHITE
            } else {
                frontPixels[offset + x] = Color.WHITE
            }
        }
    }

    val back = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
        setPixels(backPixels, 0, width, 0, 0, width, height)
    }
    val front = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
        setPixels(frontPixels, 0, width, 0, 0, width, height)
    }
    return Pair(back, front)

}


