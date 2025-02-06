package com.vvl.loyalty_cards.features.impl.add_loyalty_card.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import loyaltycards.features.impl.addloyaltycard.generated.resources.Res
import loyaltycards.features.impl.addloyaltycard.generated.resources.camera_permission_missing
import loyaltycards.features.impl.addloyaltycard.generated.resources.open_settings
import org.jetbrains.compose.resources.stringResource

@Composable
@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun AddLoyaltyCardView(component: AddLoyaltyCardComponent) {
    if (component.useExternalBarcodeScanner.collectAsState().value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
        val controller: PermissionsController =
            remember(factory) { factory.createPermissionsController() }

        BindEffect(controller)

        LaunchedEffect(Unit) {
            component
                .requestPermission
                .collect {
                    try {
                        controller.providePermission(Permission.CAMERA)
                        component.onPermissionResultReceived(true)
                    } catch (e: Exception) {
                        component.onPermissionResultReceived(false)
                    }
                }
        }

        if (component.wasPermissionGranted.collectAsState().value) {
            PlatformCameraPreviewView(component)
        } else {
            Scaffold(Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(Res.string.camera_permission_missing),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(16.dp))
                        Button({ controller.openAppSettings() }) {
                            Text(stringResource(Res.string.open_settings))
                        }
                    }
                }
            }
        }
    }
}
