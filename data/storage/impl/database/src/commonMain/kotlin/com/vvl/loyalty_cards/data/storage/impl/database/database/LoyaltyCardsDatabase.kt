package com.vvl.loyalty_cards.data.storage.impl.database.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.vvl.loyalty_cards.data.storage.impl.database.converter.Converters
import com.vvl.loyalty_cards.data.storage.impl.database.model.DBLoyaltyCard
import com.vvl.loyalty_cards.data.storage.impl.database.model.DBWidgetState

internal const val DB_FILE_NAME = "loyalty_cards.db"

@Database(entities = [DBLoyaltyCard::class, DBWidgetState::class], version = 1)
@ConstructedBy(LoyaltyCardsDatabaseConstructor::class)
@TypeConverters(Converters::class)
internal abstract class LoyaltyCardsDatabase : RoomDatabase() {
    abstract fun loyaltyCardsDao(): LoyaltyCardsDao
    abstract fun widgetDao(): WidgetDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect object LoyaltyCardsDatabaseConstructor :
    RoomDatabaseConstructor<LoyaltyCardsDatabase> {
    override fun initialize(): LoyaltyCardsDatabase
}
