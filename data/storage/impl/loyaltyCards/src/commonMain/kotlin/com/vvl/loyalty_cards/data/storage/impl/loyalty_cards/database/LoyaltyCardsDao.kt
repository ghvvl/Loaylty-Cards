package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.model.DBLoyaltyCard
import kotlinx.coroutines.flow.Flow

@Dao
internal interface LoyaltyCardsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: DBLoyaltyCard)

    @Delete
    suspend fun delete(card: DBLoyaltyCard)

    @Query("SELECT * FROM DBLoyaltyCard")
    fun getAllAsFlow(): Flow<List<DBLoyaltyCard>>

    @Query("SELECT * FROM DBLoyaltyCARD WHERE data=:data")
    suspend fun getCardByData(data: String): DBLoyaltyCard?
}
