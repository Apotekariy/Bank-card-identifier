package com.example.bank_card_identifier.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Number(
    @SerialName("length")
    val length: Int? = null,
    @SerialName("luhn")
    val luhn: Boolean? = null
)