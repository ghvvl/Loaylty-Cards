package com.vvl.loyalty_cards.features.common.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vvl.loyalty_cards.common.model.LoyaltyCard

@Composable
fun LoyaltyCardView(
    modifier: Modifier,
    loyaltyCard: LoyaltyCard,
    onClick: (LoyaltyCard) -> Unit
) {
    Card(
        modifier = modifier.aspectRatio(27f / 17f),
        colors = CardDefaults.cardColors(containerColor = Color(loyaltyCard.cardColor)),
        onClick = { onClick(loyaltyCard) }
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row {
                Text(loyaltyCard.data)
                Text(loyaltyCard.codeType.toString())
            }
        }
    }
}