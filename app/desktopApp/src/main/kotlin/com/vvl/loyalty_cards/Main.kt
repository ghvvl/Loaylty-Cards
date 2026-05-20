package com.vvl.loyalty_cards

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.vvl.loyalty_cards.app.App

private fun main() {
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "YLC",
            alwaysOnTop = true,
            state = rememberWindowState(width = 600.dp, height = 800.dp),
        ) {
            App()
        }
    }
}