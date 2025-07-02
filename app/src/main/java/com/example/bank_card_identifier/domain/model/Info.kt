package com.example.bank_card_identifier.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("number")
    val number: Number? = null,
    @SerialName("scheme")
    val scheme: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("prepaid")
    val prepaid: Boolean? = null,
    @SerialName("country")
    val country: Country? = null,
    @SerialName("bank")
    val bank: Bank? = null
)