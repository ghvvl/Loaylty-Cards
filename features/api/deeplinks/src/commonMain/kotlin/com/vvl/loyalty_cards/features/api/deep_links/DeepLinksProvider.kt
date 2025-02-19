package com.vvl.loyalty_cards.features.api.deep_links

interface DeepLinksProvider {

    fun createOpenCardDetailsDeepLink(cardData: String): String
}
