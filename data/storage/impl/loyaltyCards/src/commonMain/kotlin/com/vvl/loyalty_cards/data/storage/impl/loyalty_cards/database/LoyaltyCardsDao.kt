package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.model.DBLoyaltyCard
import kotlinx.coroutines.flow.Flow

@Dao
internal interface LoyaltyCardsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: DBLoyaltyCard)

    @Query("DELETE FROM DBLoyaltyCard WHERE data = :data")
    suspend fun deleteByData(data: String)

    @Query("SELECT * FROM DBLoyaltyCard ORDER BY id DESC")
    fun getAllAsFlow(): Flow<List<DBLoyaltyCard>>
}
