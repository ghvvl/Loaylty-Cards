package com.vvl.loyalty_cards.features.impl.loyalty_card_details.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent

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
                actions = { }
            )
        },
        content = { DrawContent(component, it) }
    )
}

@Composable
private fun DrawContent(component: WidgetDetailsComponent, paddingValues: PaddingValues) {

}
