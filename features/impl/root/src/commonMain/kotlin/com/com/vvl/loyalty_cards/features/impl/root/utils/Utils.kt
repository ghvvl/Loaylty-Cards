package com.com.vvl.loyalty_cards.features.impl.root.utils

import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.StackAnimation
import com.arkivanov.essenty.backhandler.BackHandler

expect fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T>
