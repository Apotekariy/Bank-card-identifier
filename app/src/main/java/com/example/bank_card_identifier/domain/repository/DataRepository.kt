package com.example.bank_card_identifier.domain.repository

import com.example.bank_card_identifier.data.remote.dto.InfoResponse

interface DataRepository {
    suspend fun getInfoById(id: String): InfoResponse
}