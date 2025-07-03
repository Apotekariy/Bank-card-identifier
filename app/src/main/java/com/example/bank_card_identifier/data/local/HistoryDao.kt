package com.example.bank_card_identifier.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: HistoryItem)

    @Query("SELECT * FROM history_items ORDER BY timestamp DESC")
    fun getAll(): Flow<List<HistoryItem>>
}