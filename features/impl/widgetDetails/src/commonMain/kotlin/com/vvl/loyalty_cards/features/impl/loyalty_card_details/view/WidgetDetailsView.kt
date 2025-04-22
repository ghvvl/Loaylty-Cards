package com.vvl.loyalty_cards.features.impl.loyalty_card_details.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent
import com.vvl.loyalty_cards.features.api.widget_details.model.UIWidgetLoyaltyCards
import com.vvl.loyalty_cards.features.common.view.LoyaltyCardView
import loyaltycards.features.impl.widgetdetails.generated.resources.Res
import loyaltycards.features.impl.widgetdetails.generated.resources.widget_details_empty_cards
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.WidgetDetailsView(
    component: WidgetDetailsComponent,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(component.providedWidgetId.id),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                        text = component.providedWidgetId.id
                    )
                },
                navigationIcon = {
                    IconButton(onClick = component::onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    if (component.showResetButton.collectAsState().value) {
                        IconButton(onClick = component::onResetClicked) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                    if (component.showRemoveAllButton.collectAsState().value) {
                        IconButton(onClick = component::onRemoveAllClicked) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                }
            )
        },
        content = {
            val loyaltyCards by component.loyaltyCards.collectAsState(emptyList())
            if (loyaltyCards.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        text = stringResource(Res.string.widget_details_empty_cards),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                DrawContent(
                    component.isSingleSelection,
                    loyaltyCards,
                    component::onLoyaltyCardClicked,
                    it
                )
            }
        }
    )
}

@Composable
private fun DrawContent(
    isSingleSelection: Boolean,
    loyaltyCards: List<UIWidgetLoyaltyCards>,
    onLoyaltyCardClicked: (LoyaltyCard) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(loyaltyCards, key = { it.card.data }) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLoyaltyCardClicked(item.card) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                LoyaltyCardView(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    loyaltyCard = item.card,
                    onClick = null
                )
                if (isSingleSelection) {
                    RadioButton(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        selected = item.isChecked,
                        onClick = { onLoyaltyCardClicked(item.card) }
                    )
                } else {
                    Checkbox(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        checked = item.isChecked,
                        onCheckedChange = { onLoyaltyCardClicked(item.card) }
                    )
                }
            }
        }
    }
}
