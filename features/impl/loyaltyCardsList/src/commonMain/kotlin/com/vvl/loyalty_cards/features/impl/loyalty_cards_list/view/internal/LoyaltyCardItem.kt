package com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view.internal

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.features.common.view.LoyaltyCardView

@OptIn(ExperimentalSharedTransitionApi::class)
@Suppress("MagicNumber")
@Composable
internal fun SharedTransitionScope.LoyaltyCardItem(
    modifier: Modifier,
    card: LoyaltyCard,
    onSwipe: (LoyaltyCard) -> Unit,
    onClick: (LoyaltyCard) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onSwipe(card)
            }
            true
        }
    )

    val shape = CardDefaults.shape
    SwipeToDismissBox(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        state = dismissState,
        backgroundContent = {
            if (dismissState.progress == 1f) return@SwipeToDismissBox

            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.Settled -> Color.Red
                    SwipeToDismissBoxValue.StartToEnd -> Color.Transparent
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                }
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color = color, shape = shape)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }
        },
        enableDismissFromStartToEnd = false
    ) {
        LoyaltyCardView(
            modifier = Modifier
                .fillMaxWidth()
                .sharedElement(
                    state = rememberSharedContentState(card.data),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            loyaltyCard = card,
            onClick = onClick
        )
    }
}
