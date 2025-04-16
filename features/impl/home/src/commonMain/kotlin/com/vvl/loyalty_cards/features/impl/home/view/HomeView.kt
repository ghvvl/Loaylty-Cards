package com.vvl.loyalty_cards.features.impl.home.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.vvl.loyalty_cards.features.api.home.component.HomeComponent
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.widgets_list.component.WidgetsListComponent
import loyaltycards.features.impl.home.generated.resources.Res
import loyaltycards.features.impl.home.generated.resources.loyalty_cards_list_item_title
import loyaltycards.features.impl.home.generated.resources.widgets_item_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeView(
    component: HomeComponent,
    loyaltyCardsListView: @Composable (LoyaltyCardsListComponent) -> Unit,
    widgetsListView: @Composable (WidgetsListComponent) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(Modifier.fillMaxSize()) {
        val childStack by component.childStack.subscribeAsState()
        val activeComponent = childStack.active.instance

        Children(
            modifier = Modifier
                .consumeWindowInsets(WindowInsets.navigationBars)
                .weight(1f),
            stack = childStack
        ) {
            when (val child = it.instance) {
                is HomeComponent.HomeChild.LoyaltyCardsList -> {
                    loyaltyCardsListView(child.component)
                }

                is HomeComponent.HomeChild.WidgetsList -> {
                    widgetsListView(child.component)
                }
            }
        }

        val modifier = with(animatedVisibilityScope) {
            Modifier
                .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f)
                .animateEnterExit(
                    enter = fadeIn() + slideInVertically { it },
                    exit = fadeOut() + slideOutVertically { it }
                )
        }
        NavigationBar(modifier) {
            NavigationBarItem(
                selected = activeComponent is HomeComponent.HomeChild.LoyaltyCardsList,
                onClick = component::onLoyaltyCardsListClicked,
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ViewList,
                        contentDescription = "Localized description"
                    )
                },
                label = { Text(stringResource(Res.string.loyalty_cards_list_item_title)) }
            )
            NavigationBarItem(
                selected = activeComponent is HomeComponent.HomeChild.WidgetsList,
                onClick = component::onWidgetStatesListClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Widgets,
                        contentDescription = "Localized description"
                    )
                },
                label = { Text(stringResource(Res.string.widgets_item_title)) }
            )
        }
    }
}
