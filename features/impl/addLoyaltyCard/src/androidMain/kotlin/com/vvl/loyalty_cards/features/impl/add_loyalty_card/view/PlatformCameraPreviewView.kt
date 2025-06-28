package com.vvl.loyalty_cards.features.impl.add_loyalty_card.view

import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils.mapToLoyaltyCardCodeType
import java.util.concurrent.Executors

@OptIn(ExperimentalGetImage::class)
@Suppress("UnsafeCallOnNullableType")
@Composable
internal actual fun PlatformCameraPreviewView(component: AddLoyaltyCardComponent) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scanner = remember {
        BarcodeScanning.getClient(
            BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                .build()
        )
    }
    val cameraController: LifecycleCameraController = remember {
        LifecycleCameraController(context).apply {
            setImageAnalysisAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
                scanner
                    .process(
                        InputImage.fromMediaImage(
                            imageProxy.image!!, imageProxy.imageInfo.rotationDegrees
                        )
                    )
                    .addOnSuccessListener { barcodes ->
                        if (barcodes.isNotEmpty()) {
                            with(barcodes.first()) {
                                component.onCodeReceived(
                                    rawValue.orEmpty(),
                                    format.mapToLoyaltyCardCodeType()
                                )
                            }
                        }
                    }
                    .addOnCompleteListener { imageProxy.close() }
            }
            bindToLifecycle(lifecycleOwner)
        }
    }
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { c ->
            PreviewView(c).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                controller = cameraController
            }
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            cameraController.unbind()
            scanner.close()
        }
    }
}
