package com.vvl.loyalty_cards.features.impl.loyalty_card_details.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.data.storage.api.widget.storage.WidgetStorage
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent
import com.vvl.loyalty_cards.features.api.widget_details.model.UIWidgetLoyaltyCards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

internal class WidgetDetailsComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    override val providedWidgetId: WidgetId,
    private val widgetStorage: WidgetStorage,
    loyaltyCardsStorage: LoyaltyCardsStorage,
    private val widgetDelegate: WidgetDelegate
) : WidgetDetailsComponent, ComponentContext by componentContext {

    private val scope = coroutineScope()

    override val isSingleSelection: Boolean = false

    override val loyaltyCards: Flow<List<UIWidgetLoyaltyCards>> =
        combine(
            widgetStorage.getWidgetStateFlow(providedWidgetId),
            loyaltyCardsStorage.loyaltyCards
        ) { widgetState, loyaltyCards -> loyaltyCards.map { it.map(widgetState.widgetCards) } }

    override fun onLoyaltyCardClicked(card: LoyaltyCard) {
        scope.launch {
            val widgetState =
                widgetStorage.getWidgetStateFlow(providedWidgetId).firstOrNull() ?: return@launch

            val newWidgetState = widgetState.copy(
                widgetCards = createNewWidgetCards(isSingleSelection, widgetState.widgetCards, card)
            )
            widgetStorage.updateWidgetState(newWidgetState)
            widgetDelegate.updateAllWidgets()
        }
    }

    private fun createNewWidgetCards(
        isSingleSelection: Boolean,
        widgetCards: Set<LoyaltyCard>,
        clickedCard: LoyaltyCard
    ): Set<LoyaltyCard> {
        if (isSingleSelection) return setOf(clickedCard)

        return if (widgetCards.contains(clickedCard)) {
            widgetCards - clickedCard
        } else {
            widgetCards + clickedCard
        }
    }

    override fun onBackClicked() = rootNavigator.onBackClicked()

    private fun LoyaltyCard.map(
        checkedCards: Set<LoyaltyCard>
    ): UIWidgetLoyaltyCards = UIWidgetLoyaltyCards(
        this,
        checkedCards.contains(this)
    )
}