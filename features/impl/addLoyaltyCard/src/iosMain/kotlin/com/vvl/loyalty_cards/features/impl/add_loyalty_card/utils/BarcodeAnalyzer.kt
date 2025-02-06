package com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils

import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVCaptureDeviceInput
import platform.AVFoundation.AVCaptureMetadataOutput
import platform.AVFoundation.AVCaptureMetadataOutputObjectsDelegateProtocol
import platform.AVFoundation.AVCaptureSession
import platform.AVFoundation.AVCaptureVideoOrientationLandscapeLeft
import platform.AVFoundation.AVCaptureVideoOrientationLandscapeRight
import platform.AVFoundation.AVCaptureVideoOrientationPortrait
import platform.AVFoundation.AVCaptureVideoOrientationPortraitUpsideDown
import platform.AVFoundation.AVCaptureVideoPreviewLayer
import platform.AVFoundation.AVLayerVideoGravityResizeAspectFill
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.AVMetadataMachineReadableCodeObject
import platform.AVFoundation.AVMetadataObjectTypeAztecCode
import platform.AVFoundation.AVMetadataObjectTypeCodabarCode
import platform.AVFoundation.AVMetadataObjectTypeCode128Code
import platform.AVFoundation.AVMetadataObjectTypeCode39Code
import platform.AVFoundation.AVMetadataObjectTypeCode93Code
import platform.AVFoundation.AVMetadataObjectTypeDataMatrixCode
import platform.AVFoundation.AVMetadataObjectTypeEAN13Code
import platform.AVFoundation.AVMetadataObjectTypeEAN8Code
import platform.AVFoundation.AVMetadataObjectTypeITF14Code
import platform.AVFoundation.AVMetadataObjectTypePDF417Code
import platform.AVFoundation.AVMetadataObjectTypeQRCode
import platform.AVFoundation.AVMetadataObjectTypeUPCECode
import platform.CoreGraphics.CGRect
import platform.Foundation.NSError
import platform.QuartzCore.CALayer
import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceOrientation
import platform.darwin.NSObject
import platform.darwin.dispatch_get_main_queue

@OptIn(ExperimentalForeignApi::class)
internal class BarcodeAnalyzer(
    private val scope: CoroutineScope,
    private val onScanned: (String, LoyaltyCardCodeType) -> Unit
) : AVCaptureMetadataOutputObjectsDelegateProtocol, NSObject() {

    private lateinit var previewLayer: AVCaptureVideoPreviewLayer
    private val captureSession: AVCaptureSession by lazy { AVCaptureSession() }

    @Suppress("ReturnCount")
    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    fun prepare(layer: CALayer) {
        val device = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo) ?: return

        val videoInput = memScoped {
            val error: ObjCObjectVar<NSError?> = alloc<ObjCObjectVar<NSError?>>()
            val videoInput = AVCaptureDeviceInput(device = device, error = error.ptr)
            if (error.value != null) {
                null
            } else {
                videoInput
            }
        }
        if (videoInput != null && captureSession.canAddInput(videoInput)) {
            captureSession.addInput(videoInput)
        } else {
            return
        }

        val metadataOutput = AVCaptureMetadataOutput()
        if (captureSession.canAddOutput(metadataOutput)) {
            captureSession.addOutput(metadataOutput)
            metadataOutput.setMetadataObjectsDelegate(this, queue = dispatch_get_main_queue())
            metadataOutput.metadataObjectTypes = metadataOutput.availableMetadataObjectTypes
        } else {
            return
        }

        previewLayer = AVCaptureVideoPreviewLayer(session = captureSession)
        previewLayer.apply {
            frame = layer.bounds
            videoGravity = AVLayerVideoGravityResizeAspectFill
            setCurrentOrientation(newOrientation = UIDevice.currentDevice.orientation)
            layer.addSublayer(this)
        }

        scope.launch(Dispatchers.Default) {
            captureSession.startRunning()
        }
    }

    fun setCurrentOrientation(newOrientation: UIDeviceOrientation) {
        previewLayer.connection?.videoOrientation = when (newOrientation) {
            UIDeviceOrientation.UIDeviceOrientationLandscapeLeft -> AVCaptureVideoOrientationLandscapeRight
            UIDeviceOrientation.UIDeviceOrientationLandscapeRight -> AVCaptureVideoOrientationLandscapeLeft
            UIDeviceOrientation.UIDeviceOrientationPortrait -> AVCaptureVideoOrientationPortrait
            UIDeviceOrientation.UIDeviceOrientationPortraitUpsideDown -> AVCaptureVideoOrientationPortraitUpsideDown
            else -> AVCaptureVideoOrientationPortrait
        }
    }

    fun setFrame(rect: CValue<CGRect>) {
        previewLayer.setFrame(rect)
    }

    override fun captureOutput(
        output: platform.AVFoundation.AVCaptureOutput,
        didOutputMetadataObjects: List<*>,
        fromConnection: platform.AVFoundation.AVCaptureConnection
    ) {
        val metadataObject =
            didOutputMetadataObjects.firstOrNull() as? AVMetadataMachineReadableCodeObject
        metadataObject?.let {
            captureSession.stopRunning()
            onScanned(it.stringValue!!, it.type!!.toLoyaltyCardCodeType())
        }
    }
}

private fun String.toLoyaltyCardCodeType(): LoyaltyCardCodeType =
    when (this) {
        AVMetadataObjectTypeQRCode -> LoyaltyCardCodeType.QR_CODE
        AVMetadataObjectTypeCodabarCode -> LoyaltyCardCodeType.CODABAR
        AVMetadataObjectTypeCode39Code -> LoyaltyCardCodeType.CODE_39
        AVMetadataObjectTypeCode93Code -> LoyaltyCardCodeType.CODE_93
        AVMetadataObjectTypeCode128Code -> LoyaltyCardCodeType.CODE_128
        AVMetadataObjectTypeEAN8Code -> LoyaltyCardCodeType.EAN_8
        AVMetadataObjectTypeEAN13Code -> LoyaltyCardCodeType.EAN_13
        AVMetadataObjectTypeITF14Code -> LoyaltyCardCodeType.ITF
        AVMetadataObjectTypeUPCECode -> LoyaltyCardCodeType.UPC_E
        AVMetadataObjectTypeDataMatrixCode -> LoyaltyCardCodeType.DATA_MATRIX
        AVMetadataObjectTypePDF417Code -> LoyaltyCardCodeType.PDF_417
        AVMetadataObjectTypeAztecCode -> LoyaltyCardCodeType.AZTEC
        else -> throw IllegalArgumentException("$this bar code type doesn't supported!")
    }
