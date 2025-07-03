package com.example.bank_card_identifier.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bank_card_identifier.domain.model.Info

@Composable
fun BankCardScreen(
    viewModel: BankCardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var textFieldValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(textFieldValue) {
        viewModel.updateCardNumber(textFieldValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SearchSection(
                textFieldValue = textFieldValue,
                onValueChange = { textFieldValue = it },
                onSearchClick = {
                    viewModel.fetchCardInfo()
                    keyboardController?.hide() },
                isEnabled = textFieldValue.length >= 6,
                isLoading = uiState is BankCardUiState.Loading
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            when (val state = uiState) {
                is BankCardUiState.Initial -> PlaceholderScreen()
                BankCardUiState.Loading -> LoadingIndicator()
                is BankCardUiState.Success -> CardInfoDisplay(cardInfo = state.cardInfo)
                is BankCardUiState.Error -> ErrorMessage(message = state.message)
            }
        }
    }
}

@Composable
private fun SearchSection(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    isEnabled: Boolean,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Поле ввода номера карты
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            label = { Text("Enter card number") },
            placeholder = { Text("12345678") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                }
            }
        )

        // Кнопка запроса
        Button(
            onClick = onSearchClick,
            enabled = isEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Card Info")
        }
    }
}

@Composable
private fun PlaceholderScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Enter first 8+ digits of your card",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $message",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun CardInfoDisplay(cardInfo: Info) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SectionTitle("Card Information")
            InfoRow("Scheme", cardInfo.scheme)
            InfoRow("Type", cardInfo.type)
            InfoRow("Brand", cardInfo.brand)
            InfoRow("Prepaid", cardInfo.prepaid?.let { if (it) "Yes" else "No" })

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("Number Details")
            cardInfo.number?.let { number ->
                InfoRow("Length", number.length?.toString())
                InfoRow("Luhn Check", number.luhn?.let { if (it) "Passed" else "Failed" })
            }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("Bank Information")
            cardInfo.bank?.let { bank ->
                InfoRow("Name", bank.name)
                InfoRow("City", bank.city)
                InfoRow("Phone", bank.phone)
                InfoRow("Website", bank.url)
            }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("Country Information")
            cardInfo.country?.let { country ->
                InfoRow("Name", country.name)
                InfoRow("Currency", country.currency)
                InfoRow("Code", country.alpha2)
                InfoRow("Emoji", country.emoji)
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun InfoRow(label: String, value: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Text(
            value ?: "not found",
            style = MaterialTheme.typography.bodyMedium,
            color = if (value == null) MaterialTheme.colorScheme.error
            else MaterialTheme.colorScheme.onSurface
        )
    }
}