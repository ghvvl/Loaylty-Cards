package com.vvl.loyalty_cards.features.impl.deep_links.manager

import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksHandler
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksProvider
import dev.theolm.rinku.listenForDeepLinks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val DEEP_LINKS_SCHEME = "loyalty.cards"
private const val DEEP_LINK_OPEN_CARD_DETAILS = "open.card.details.screen"
private const val DEEP_LINK_OPEN_CARD_DETAILS_CARD_ID = "cardId"

internal class DeepLinksManager(scope: CoroutineScope) : DeepLinksProvider, DeepLinksHandler {

    private var openCardDetailsAction: ((String) -> Unit)? = null

    init {
        scope.launch {
            listenForDeepLinks { deepLink ->
                if (deepLink.host == DEEP_LINK_OPEN_CARD_DETAILS) {
                    val cardId = deepLink.parameters[DEEP_LINK_OPEN_CARD_DETAILS_CARD_ID]
                        ?: return@listenForDeepLinks

                    openCardDetailsAction?.invoke(cardId)
                }
            }
        }
    }

    override fun createOpenCardDetailsDeepLink(cardData: String): String =
        "$DEEP_LINKS_SCHEME://$DEEP_LINK_OPEN_CARD_DETAILS?$DEEP_LINK_OPEN_CARD_DETAILS_CARD_ID=$cardData"

    override fun validateDeeplink(url: String): Boolean = url.startsWith(DEEP_LINKS_SCHEME)

    override fun addOpenCardDetailsDeepLinkListener(action: (String) -> Unit) {
        openCardDetailsAction = action
    }

    override fun removeOpenCardDetailsDeepLinkListener() {
        openCardDetailsAction = null
    }
}
