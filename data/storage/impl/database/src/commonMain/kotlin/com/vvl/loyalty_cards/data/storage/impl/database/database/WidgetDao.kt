package com.vvl.loyalty_cards.data.storage.impl.database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.data.storage.impl.database.model.DBWidgetState
import kotlinx.coroutines.flow.Flow

@Dao
interface WidgetDao {
    @Query("SELECT * FROM DBWidgetState")
    fun getAllAsFlow(): Flow<List<DBWidgetState>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(widgetState: DBWidgetState)

    @Query("DELETE FROM DBWidgetState WHERE widgetId=:widgetId")
    suspend fun deleteById(widgetId: WidgetId)
}
