package com.example.bank_card_identifier.data.repository

import com.example.bank_card_identifier.data.local.HistoryDao
import com.example.bank_card_identifier.data.local.HistoryItem
import com.example.bank_card_identifier.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryRepository {

    override suspend fun saveHistoryItem(bin: String, jsonResponse: String) {
        historyDao.insert(HistoryItem(bin = bin, jsonResponse = jsonResponse))
    }

    override fun getAllHistory(): Flow<List<HistoryItem>> {
        return historyDao.getAll()
    }
}