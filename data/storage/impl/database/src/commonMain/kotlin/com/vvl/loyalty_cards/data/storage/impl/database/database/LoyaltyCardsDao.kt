package com.vvl.loyalty_cards.data.storage.impl.database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vvl.loyalty_cards.data.storage.impl.database.model.DBLoyaltyCard
import kotlinx.coroutines.flow.Flow

@Dao
interface LoyaltyCardsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: DBLoyaltyCard)

    @Query("DELETE FROM DBLoyaltyCard WHERE data = :data")
    suspend fun deleteByData(data: String)

    @Query("SELECT * FROM DBLoyaltyCard ORDER BY id DESC")
    fun getAllAsFlow(): Flow<List<DBLoyaltyCard>>

    @Transaction
    suspend fun replaceData(newCards: List<DBLoyaltyCard>) {
        clearAll()
        insertAll(newCards)
    }

    @Query("DELETE FROM DBLoyaltyCard")
    suspend fun clearAll()

    @Insert
    suspend fun insertAll(cards: List<DBLoyaltyCard>)
}
