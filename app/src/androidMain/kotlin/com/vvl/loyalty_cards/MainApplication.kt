package com.vvl.loyalty_cards

import android.app.Application
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.impl.loyalty_cards.storage.LoyaltyCardsStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                module {
                    androidContext(this@MainApplication)
                    single<LoyaltyCardsStorage> { LoyaltyCardsStorageImpl(get()) }
                }
            )
        }
    }
}