package com.example.bank_card_identifier.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "history_items")
data class Info(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
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
    val bank: Bank? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val bin: String = ""
)