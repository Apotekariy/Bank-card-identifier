package com.example.bank_card_identifier.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("numeric")
    val numeric: String? = null,
    @SerialName("alpha2")
    val alpha2: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("emoji")
    val emoji: String? = null,
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("latitude")
    val latitude: Int? = null,
    @SerialName("longitude")
    val longitude: Int? = null
)