package com.vvl.loyalty_cards.features.impl.widgets_list.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.common.model.WidgetState
import com.vvl.loyalty_cards.features.api.widgets_list.component.WidgetsListComponent
import loyaltycards.features.impl.widgetslist.generated.resources.Res
import loyaltycards.features.impl.widgetslist.generated.resources.widgets_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.WidgetsListView(
    component: WidgetsListComponent,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.widgets_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    ) { paddingValues ->
        val widgets by component.widgets.collectAsState(emptyList())
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(widgets, key = { it.widgetId.id }) { item ->
                WidgetItem(
                    item,
                    component::onWidgetClicked
                )/* {
                    sharedBounds(
                        sharedContentState = rememberSharedContentState(key),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }*/
            }
        }
    }
}

@Composable
private fun WidgetItem(widgetState: WidgetState, onClick: (WidgetId) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(widgetState.widgetId) }
    ) {
        Text(widgetState.widgetId.id)
    }
}
