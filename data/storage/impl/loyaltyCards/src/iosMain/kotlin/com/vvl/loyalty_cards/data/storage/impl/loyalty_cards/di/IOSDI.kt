package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database.DB_FILE_NAME
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database.LoyaltyCardsDatabase
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL

internal actual fun createDatabase(): LoyaltyCardsDatabase {
    val documentDirectory: NSURL? = NSFileManager
        .defaultManager
        .containerURLForSecurityApplicationGroupIdentifier("group.com.vvl.loyalty_cards")
    val dbFile = requireNotNull(documentDirectory).path + "/$DB_FILE_NAME"
    return Room.databaseBuilder<LoyaltyCardsDatabase>(dbFile)
        .setDriver(BundledSQLiteDriver())
        .build()
}
