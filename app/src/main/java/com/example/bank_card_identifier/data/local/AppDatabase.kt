package com.example.bank_card_identifier.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HistoryItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}