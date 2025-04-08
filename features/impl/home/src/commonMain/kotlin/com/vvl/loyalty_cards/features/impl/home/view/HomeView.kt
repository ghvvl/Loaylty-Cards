package com.vvl.loyalty_cards.features.impl.home.view

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.vvl.loyalty_cards.features.api.home.component.HomeComponent
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeView(
    component: HomeComponent,
    loyaltyCardsListView: @Composable (LoyaltyCardsListComponent) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        val childStack by component.childStack.subscribeAsState()
        val activeComponent = childStack.active.instance

        Children(
            stack = childStack,
            modifier = Modifier.weight(1f),
        ) {
            when (val child = it.instance) {
                is HomeComponent.HomeChild.LoyaltyCardsList -> {
                    loyaltyCardsListView(child.component)
                }
            }
        }
        NavigationBar {
            NavigationBarItem(
                selected = activeComponent is HomeComponent.HomeChild.LoyaltyCardsList,
                onClick = { component.onLoyaltyCardsListClicked() },
                icon = {
                    /* Icon(
                         modifier = Modifier.size(24.dp),
                         imageVector = vectorResource(
                             if (item.isSelected) item.type.iconSelected
                             else item.type.iconUnselected
                         ),
                         contentDescription = item.type.iconDescription
                     )*/
                },
                label = {
                    /*    Text(
                            modifier = Modifier.requiredWidth(IntrinsicSize.Max),
                            text = stringResource(item.title),
                            style = MainTypography.CaptionRegularS()
                        )*/
                }
            )
        }
    }
}