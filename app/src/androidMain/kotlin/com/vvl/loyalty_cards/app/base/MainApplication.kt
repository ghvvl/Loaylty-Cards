package com.vvl.loyalty_cards.app.base

import android.app.Application
import android.content.ComponentName
import android.content.Context
import com.vvl.loyalty_cards.app.di.appModule
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.di.androidAddLoyaltyCardModule
import com.vvl.loyalty_cards.features.impl.widget.di.androidWidgetModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.binds
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools

internal class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            logger(KoinPlatformTools.defaultLogger(Level.INFO))

            modules(
                listOf(
                    appModule,
                    androidAddLoyaltyCardModule,
                    androidWidgetModule,
                    module {
                        single { this@MainApplication } binds arrayOf(
                            Context::class,
                            Application::class
                        )

                        single {
                            ComponentName(
                                "com.vvl.loyalty_cards",
                                "com.vvl.loyalty_cards.app.base.MainActivity"
                            )
                        }
                    }
                )
            )
        }
    }
}
