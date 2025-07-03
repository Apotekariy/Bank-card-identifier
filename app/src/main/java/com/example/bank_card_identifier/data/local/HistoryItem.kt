package com.example.bank_card_identifier.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_items")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val bin: String,
    val timestamp: Long = System.currentTimeMillis(),
    val jsonResponse: String
)