package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.model.DBLoyaltyCard

internal const val DB_FILE_NAME = "loyalty_cards.db"

@Database(entities = [DBLoyaltyCard::class], version = 1)
@ConstructedBy(LoyaltyCardsDatabaseConstructor::class)
internal abstract class LoyaltyCardsDatabase : RoomDatabase() {
    abstract fun loyaltyCardsDao(): LoyaltyCardsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect object LoyaltyCardsDatabaseConstructor : RoomDatabaseConstructor<LoyaltyCardsDatabase> {
    override fun initialize(): LoyaltyCardsDatabase
}
