package com.vvl.loyalty_cards.features.impl.loyalty_card_details.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.loyalty_card_details.model.BrightnessMode
import com.vvl.loyalty_cards.features.common.view.LoyaltyCardView
import loyaltycards.features.impl.loyaltycarddetails.generated.resources.Res
import loyaltycards.features.impl.loyaltycarddetails.generated.resources.loyalty_card_name
import org.jetbrains.compose.resources.stringResource

private const val MAX_LOYALTY_CARD_NAME_LENGTH = 32

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LoyaltyCardDetailsView(
    component: LoyaltyCardDetailsComponent,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = component::onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    val brightnessMode by component.brightnessMode.subscribeAsState()
                    val icon = when (brightnessMode) {
                        BrightnessMode.AUTO -> Icons.Filled.BrightnessHigh
                        BrightnessMode.MAX -> Icons.Filled.BrightnessAuto
                    }
                    if (component.showResetButton.subscribeAsState().value) {
                        IconButton(onClick = component::onResetClicked) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                    IconButton(onClick = component::onBrightnessClicked) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = {
            val loyaltyCard by component.loyaltyCard.subscribeAsState()

            DrawContent(
                loyaltyCard,
                component::onLoyaltyCardNameChanged,
                animatedVisibilityScope,
                it
            )
        }
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.DrawContent(
    loyaltyCard: LoyaltyCard,
    onLoyaltyCardNameChanged: (String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loyaltyCard.name,
            onValueChange = { onLoyaltyCardNameChanged(it.take(MAX_LOYALTY_CARD_NAME_LENGTH)) },
            label = { Text(stringResource(Res.string.loyalty_card_name)) },
            supportingText = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text("${loyaltyCard.name.length}/$MAX_LOYALTY_CARD_NAME_LENGTH")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            maxLines = 1
        )
        LoyaltyCardView(
            modifier = Modifier
                .fillMaxWidth()
                .sharedElement(
                    state = rememberSharedContentState(loyaltyCard.data),
                    animatedVisibilityScope = animatedVisibilityScope,
                    renderInOverlayDuringTransition = false
                )
                .align(Alignment.Center),
            loyaltyCard = loyaltyCard,
            onClick = null
        )
    }
}
