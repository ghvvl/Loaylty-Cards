package com.vvl.loyalty_cards.features.impl.loyalty_cards_list.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LoyaltyCardsListComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val loyaltyCardsStorage: LoyaltyCardsStorage,
    private val widgetDelegate: WidgetDelegate
) : LoyaltyCardsListComponent, ComponentContext by componentContext {

    private val _loyaltyCards = MutableStateFlow<List<LoyaltyCard>>(emptyList())

    override val loyaltyCards = _loyaltyCards

    private val scope = coroutineScope()

    init {
        loyaltyCardsStorage.loyaltyCards.onEach { _loyaltyCards.emit(it) }.launchIn(scope)
    }

    override fun onLoyaltyCardPositionChanged(from: Int, to: Int) {
        scope.launch {
            _loyaltyCards.update {
                it.toMutableList().apply {
                    add(to, removeAt(from))
                }
            }
            updateDataInDB()
        }
    }

    private var job: Job? = null
    private fun updateDataInDB() {
        job?.cancel()
        job = scope.launch {
            delay(500)
            loyaltyCardsStorage.replaceLoyaltyCards(_loyaltyCards.value)
            widgetDelegate.updateAllWidgets()
        }
    }

    override fun onLoyaltyCardSwiped(card: LoyaltyCard) {
        scope.launch {
            loyaltyCardsStorage.removeLoyaltyCard(card)
            widgetDelegate.updateAllWidgets()
        }
    }

    override fun onLoyaltyCardClicked(card: LoyaltyCard) {
        rootNavigator.openLoyaltyCardDetails(card)
    }

    override fun onAddLoyaltyCardClicked() {
        rootNavigator.openAddLoyaltyCard()
    }
}
