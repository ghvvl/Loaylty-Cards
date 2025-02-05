package com.vvl.loyalty_cards.features.impl.loyalty_card_details.view

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.common.view.LoyaltyCardView

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LoyaltyCardDetailsView(
    component: LoyaltyCardDetailsComponent,
    isSharedElementVisible: Boolean
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {}, navigationIcon = {
                IconButton(onClick = component::onBackClicked) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            })
        }
    ) {

        Box(Modifier.fillMaxSize()) {
            LoyaltyCardView(
                modifier = Modifier
                    .height(170.dp)
                    .align(Alignment.Center)
                    .sharedElementWithCallerManagedVisibility(
                        sharedContentState = rememberSharedContentState(key = component.loyaltyCard.data),
                        visible = isSharedElementVisible
                    ),
                loyaltyCard = component.loyaltyCard,
                onClick = {}
            )
        }
    }
}