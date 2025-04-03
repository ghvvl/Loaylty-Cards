package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di

import android.app.Application
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database.DB_FILE_NAME
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database.LoyaltyCardsDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.mp.KoinPlatform

internal actual fun createDatabase(): LoyaltyCardsDatabase {
    val application = KoinPlatform.getKoin().get<Application>()
    val dbFile = application.getDatabasePath(DB_FILE_NAME)
    return Room.databaseBuilder<LoyaltyCardsDatabase>(
        context = application,
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
