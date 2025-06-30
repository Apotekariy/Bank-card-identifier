package com.example.bank_card_identifier.data.remote

import com.example.bank_card_identifier.data.remote.dto.InfoResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("{id}")
    suspend fun getInfoById(@Path("id") id: String): InfoResponse
}