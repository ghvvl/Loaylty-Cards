package com.vvl.loyalty_cards.features.api.deep_links

interface DeepLinksHandler {

    fun validateDeeplink(url: String): Boolean

    fun addOpenCardDetailsDeepLinkListener(action: (String) -> Unit)

    fun removeOpenCardDetailsDeepLinkListener()
}
