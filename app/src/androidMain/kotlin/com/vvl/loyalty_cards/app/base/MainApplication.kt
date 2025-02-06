package com.vvl.loyalty_cards.app.base

import android.app.Application
import com.vvl.loyalty_cards.app.di.appModule
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.di.androidAddLoyaltyCardModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.mp.KoinPlatformTools

internal class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            logger(KoinPlatformTools.defaultLogger(Level.INFO))
            androidContext(this@MainApplication)
            modules(
                listOf(
                    appModule,
                    androidAddLoyaltyCardModule
                )
            )
        }
    }
}
