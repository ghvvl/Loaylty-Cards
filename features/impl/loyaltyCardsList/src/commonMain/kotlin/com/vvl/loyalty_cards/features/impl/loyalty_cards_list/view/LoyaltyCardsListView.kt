package com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.impl.loyalty_cards_list.view.internal.LoyaltyCardItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import loyaltycards.features.impl.loyaltycardslist.generated.resources.Res
import loyaltycards.features.impl.loyaltycardslist.generated.resources.add_loyalty_card
import loyaltycards.features.impl.loyaltycardslist.generated.resources.loyalty_cards_delete_action_title
import loyaltycards.features.impl.loyaltycardslist.generated.resources.loyalty_cards_delete_title
import loyaltycards.features.impl.loyaltycardslist.generated.resources.loyalty_cards_empty_title
import loyaltycards.features.impl.loyaltycardslist.generated.resources.loyalty_cards_title
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LoyaltyCardsListView(
    component: LoyaltyCardsListComponent,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var isVisible by rememberSaveable { mutableStateOf(true) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    isVisible = false
                }

                if (available.y > 1) {
                    isVisible = true
                }

                return Offset.Zero
            }
        }
    }

    val snackbarHostState = InitSnackbarHostState(
        component.showCancelDeleteMessage,
        component::onCardDeleteCancelClicked
    )

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .nestedScroll(nestedScrollConnection),
        topBar = {
            val modifier = with(animatedVisibilityScope) {
                Modifier
                    .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f)
                    .animateEnterExit(
                        enter = fadeIn() + slideInVertically { -it },
                        exit = fadeOut() + slideOutVertically { -it }
                    )
            }
            TopAppBar(
                modifier = modifier,
                title = {
                    Text(
                        text = stringResource(Res.string.loyalty_cards_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            val modifier = with(animatedVisibilityScope) {
                Modifier
                    .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f)
                    .animateEnterExit(
                        enter = fadeIn() + slideInVertically { it },
                        exit = fadeOut() + slideOutVertically { it }
                    )
            }
            AnimatedVisibility(
                modifier = modifier,
                visible = isVisible,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                ExtendedFloatingActionButton(
                    onClick = component::onAddLoyaltyCardClicked,
                    icon = { Icon(Icons.Filled.Add, "add icon") },
                    text = { Text(stringResource(Res.string.add_loyalty_card)) },
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        val loyaltyCards by component.loyaltyCards.collectAsState(emptyList())
        if (loyaltyCards.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(Res.string.loyalty_cards_empty_title),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            val lazyListState = rememberLazyListState()
            val reorderableLazyListState =
                rememberReorderableLazyListState(lazyListState) { from, to ->
                    component.onLoyaltyCardPositionChanged(from.index, to.index)
                }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(loyaltyCards, key = { it.data }) { item ->
                    ReorderableItem(reorderableLazyListState, key = item.data) { isDragging ->
                        LoyaltyCardItem(
                            Modifier.longPressDraggableHandle(),
                            item,
                            component::onLoyaltyCardSwiped,
                            component::onLoyaltyCardClicked,
                            animatedVisibilityScope
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InitSnackbarHostState(
    showCancelDeleteMessage: Flow<List<LoyaltyCard>>,
    onCardDeleteCancelClicked: (List<LoyaltyCard>) -> Unit
): SnackbarHostState {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        showCancelDeleteMessage.collect { previousState ->
            snackbarHostState.currentSnackbarData?.dismiss()

            launch {
                val result = snackbarHostState.showSnackbar(
                    message = getString(Res.string.loyalty_cards_delete_title),
                    actionLabel = getString(Res.string.loyalty_cards_delete_action_title),
                    duration = SnackbarDuration.Long
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        onCardDeleteCancelClicked(previousState)
                    }

                    SnackbarResult.Dismissed -> {}
                }
            }
        }
    }

    return snackbarHostState
}
