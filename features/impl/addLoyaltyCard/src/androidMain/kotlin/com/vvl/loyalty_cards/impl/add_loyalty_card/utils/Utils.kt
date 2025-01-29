package com.vvl.loyalty_cards.impl.add_loyalty_card.utils

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import java.util.concurrent.Executors

@Suppress("TooGenericExceptionCaught")
internal fun setupAnalysis(
    previewView: PreviewView,
    cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    lifecycleOwner: LifecycleOwner,
    onCodeReceived: (String, LoyaltyCardCodeType) -> Unit
) {
    val cameraSelector: CameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    cameraProviderFuture.addListener(
        {
            val preview = Preview
                .Builder()
                .build()

            preview.surfaceProvider = previewView.surfaceProvider

            val imageAnalysis: ImageAnalysis =
                ImageAnalysis
                    .Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

            imageAnalysis.setAnalyzer(
                Executors.newSingleThreadExecutor(),
                BarcodeAnalyzer(onCodeReceived)
            )

            val imageCapture = ImageCapture.Builder().build()

            try {
                cameraProviderFuture.get().apply {
                    unbindAll()
                    bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture,
                        imageAnalysis
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        },
        ContextCompat.getMainExecutor(previewView.context)
    )
}