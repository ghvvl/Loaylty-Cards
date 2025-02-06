package com.com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view.internal

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.features.common.view.LoyaltyCardView

@Suppress("MagicNumber")
@Composable
internal fun LazyItemScope.LoyaltyCardItem(
    card: LoyaltyCard,
    onSwipe: (LoyaltyCard) -> Unit,
    onClick: (LoyaltyCard) -> Unit,
    sharedTransitionModifier: @Composable Modifier.(String) -> Modifier
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onSwipe(card)
            }
            true
        }
    )
    SwipeToDismissBox(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .animateItem(),
        state = dismissState,
        backgroundContent = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.Settled -> Color.Transparent
                    SwipeToDismissBoxValue.StartToEnd -> Color.Transparent
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                }
            )
            Box(Modifier.fillMaxSize().background(color))
        },
        enableDismissFromStartToEnd = false
    ) {
        LoyaltyCardView(
            modifier = Modifier
                .fillMaxWidth()
                .sharedTransitionModifier(card.data),
            loyaltyCard = card,
            onClick = onClick
        )
    }
}
