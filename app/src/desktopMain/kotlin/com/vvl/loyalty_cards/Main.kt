package com.vvl.loyalty_cards

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Loyalty Cards") {

    }
}