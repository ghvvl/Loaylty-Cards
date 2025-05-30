package com.vvl.loyalty_cards.data.storage.impl.database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.data.storage.impl.database.model.DBWidgetState
import kotlinx.coroutines.flow.Flow

@Dao
interface WidgetDao {
    @Query("SELECT * FROM DBWidgetState")
    fun getAllAsFlow(): Flow<List<DBWidgetState>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(widgetState: DBWidgetState)

    @Update
    suspend fun update(widgetState: DBWidgetState)

    @Query("DELETE FROM DBWidgetState WHERE widgetId=:widgetId")
    suspend fun deleteById(widgetId: WidgetId)

    @Query("DELETE FROM DBWidgetState WHERE widgetId IN (:widgetIds)")
    suspend fun deleteByIds(widgetIds: List<WidgetId>)
}
