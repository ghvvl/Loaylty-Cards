package com.vvl.loyalty_cards.features.api.deep_links

import com.vvl.loyalty_cards.common.model.WidgetId

interface DeepLinksProvider {

    fun createOpenCardDetailsDeepLink(cardData: String): String

    fun createOpenWidgetStateDetailsDeeplink(widgetId: WidgetId): String
}
