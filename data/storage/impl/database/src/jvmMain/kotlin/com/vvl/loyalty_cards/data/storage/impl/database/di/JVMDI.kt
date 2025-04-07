package com.vvl.loyalty_cards.data.storage.impl.database.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vvl.loyalty_cards.data.storage.impl.database.database.DB_FILE_NAME
import com.vvl.loyalty_cards.data.storage.impl.database.database.LoyaltyCardsDatabase
import java.io.File

internal actual fun createDatabase(): LoyaltyCardsDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_FILE_NAME)
    return Room.databaseBuilder<LoyaltyCardsDatabase>(dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .build()
}
