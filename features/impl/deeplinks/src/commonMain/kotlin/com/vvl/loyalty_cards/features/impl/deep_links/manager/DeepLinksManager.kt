package com.vvl.loyalty_cards.features.impl.deep_links.manager

import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksHandler
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksProvider
import dev.theolm.rinku.listenForDeepLinks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val DEEP_LINKS_SCHEME = "loyalty.cards"
private const val DEEP_LINK_OPEN_CARD_DETAILS = "open.card.details.screen"
private const val DEEP_LINK_OPEN_WIDGET_STATE_DETAILS = "open.widget.state.details.screen"
private const val DEEP_LINK_OPEN_CARD_DETAILS_CARD_ID = "cardId"
private const val DEEP_LINK_OPEN_WIDGET_STATE_DETAILS_WIDGET_ID = "widgetId"

internal class DeepLinksManager(scope: CoroutineScope) : DeepLinksProvider, DeepLinksHandler {

    private var openCardDetailsAction: ((String) -> Unit)? = null
    private var openWidgetStateDetailsAction: ((WidgetId) -> Unit)? = null

    init {
        scope.launch {
            listenForDeepLinks { deepLink ->
                when {
                    deepLink.host == DEEP_LINK_OPEN_CARD_DETAILS -> {
                        val cardId = deepLink.parameters[DEEP_LINK_OPEN_CARD_DETAILS_CARD_ID]
                            ?: return@listenForDeepLinks

                        openCardDetailsAction?.invoke(cardId)
                    }

                    deepLink.host == DEEP_LINK_OPEN_WIDGET_STATE_DETAILS -> {
                        val widgetId = WidgetId(
                            deepLink.parameters[DEEP_LINK_OPEN_WIDGET_STATE_DETAILS_WIDGET_ID]
                                ?: return@listenForDeepLinks
                        )

                        openWidgetStateDetailsAction?.invoke(widgetId)
                    }
                }
            }
        }
    }

    override fun createOpenCardDetailsDeepLink(cardData: String): String =
        "$DEEP_LINKS_SCHEME://$DEEP_LINK_OPEN_CARD_DETAILS?$DEEP_LINK_OPEN_CARD_DETAILS_CARD_ID=$cardData"

    @Suppress("MaximumLineLength", "MaxLineLength")
    override fun createOpenWidgetStateDetailsDeeplink(widgetId: WidgetId): String =
        "$DEEP_LINKS_SCHEME://$DEEP_LINK_OPEN_WIDGET_STATE_DETAILS?$DEEP_LINK_OPEN_WIDGET_STATE_DETAILS_WIDGET_ID=${widgetId.id}"

    override fun validateDeeplink(url: String): Boolean = url.startsWith(DEEP_LINKS_SCHEME)

    override fun addOpenCardDetailsDeepLinkListener(action: (String) -> Unit) {
        openCardDetailsAction = action
    }

    override fun addOpenWidgetStateDetailsDeepLinkListener(action: (WidgetId) -> Unit) {
        openWidgetStateDetailsAction = action
    }

    override fun clearDeepLinkListeners() {
        openCardDetailsAction = null
    }
}
