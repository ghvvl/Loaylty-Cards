package com.vvl.loyalty_cards.features.api.deep_links

import com.vvl.loyalty_cards.common.model.WidgetId

interface DeepLinksHandler {

    fun validateDeeplink(url: String): Boolean

    fun addOpenCardDetailsDeepLinkListener(action: (String) -> Unit)

    fun addOpenWidgetStateDetailsDeepLinkListener(action: (WidgetId) -> Unit)

    fun clearDeepLinkListeners()
}
