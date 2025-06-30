package com.example.bank_card_identifier.data.repository

import com.example.bank_card_identifier.data.remote.ApiService
import com.example.bank_card_identifier.data.remote.dto.InfoResponse
import com.example.bank_card_identifier.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DataRepository {
    override suspend fun getInfoById(id: String): InfoResponse {
        return apiService.getInfoById(id)
    }
}