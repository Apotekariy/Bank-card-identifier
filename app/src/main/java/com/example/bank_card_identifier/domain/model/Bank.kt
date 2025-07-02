package com.example.bank_card_identifier.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bank(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("city")
    val city: String? = null
)