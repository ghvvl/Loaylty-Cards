package com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils

import kotlin.random.Random

internal fun generateColor(): Int = (0xFF shl 24) or
        (Random.nextInt(256) shl 16) or
        (Random.nextInt(256) shl 8) or
        Random.nextInt(256)