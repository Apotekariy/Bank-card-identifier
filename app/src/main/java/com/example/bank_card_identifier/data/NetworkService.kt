package com.example.bank_card_identifier.data

import com.example.bank_card_identifier.data.remote.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object NetworkService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net/")
        .addConverterFactory(Json.asC)
        .build()

    val api: ApiService = retrofit.create(ApiService::class.java)

}