package com.example.bank_card_identifier.domain.repository

import com.example.bank_card_identifier.domain.model.Info

interface DataRepository {
    suspend fun getCardInfo(cardNumber: String): Info
}