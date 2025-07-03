package com.example.bank_card_identifier.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bank_card_identifier.data.local.HistoryItem
import com.example.bank_card_identifier.domain.model.Info
import kotlinx.serialization.json.Json
import java.text.DateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryItemCard(item: HistoryItem) {
    // Парсим JSON строку в объект Info
    val cardInfo = remember(item.jsonResponse) {
        runCatching {
            Json.decodeFromString<Info>(item.jsonResponse)
        }.getOrNull()
    }


    val dateFormatter = remember {
        DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM,
            DateFormat.SHORT,
            Locale.getDefault() //
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("BIN: ${item.bin}", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(4.dp))

            cardInfo?.scheme?.let {
                Text("Scheme: $it")
            }

            cardInfo?.brand?.let {
                Text("Brand: $it")
            }

            val formattedDate = dateFormatter.format(Date(item.timestamp))
            Text(
                formattedDate,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}