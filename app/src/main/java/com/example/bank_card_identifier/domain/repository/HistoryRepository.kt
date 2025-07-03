package com.example.bank_card_identifier.domain.repository

import com.example.bank_card_identifier.data.local.HistoryItem
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun saveHistoryItem(bin: String, jsonResponse: String)
    fun getAllHistory(): Flow<List<HistoryItem>>
}