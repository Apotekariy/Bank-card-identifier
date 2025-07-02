package com.example.bank_card_identifier.data.remote

import com.example.bank_card_identifier.domain.model.Info
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("{bin}")
    suspend fun getCardInfo(@Path("bin") bin: String): Info
}