package com.example.bank_card_identifier.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank_card_identifier.domain.model.Info
import com.example.bank_card_identifier.domain.repository.DataRepository
import com.example.bank_card_identifier.domain.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class BankCardViewModel @Inject constructor(
    private val repository: DataRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    // UI состояние
    private val _uiState = MutableStateFlow<BankCardUiState>(BankCardUiState.Initial)
    val uiState: StateFlow<BankCardUiState> = _uiState.asStateFlow()

    // Введенный номер карты
    private var cardNumber = ""

    // Обновление введенного номера
    fun updateCardNumber(newNumber: String) {
        cardNumber = newNumber.filter { it.isDigit() }
    }

    // Запрос информации по карте
    fun fetchCardInfo() {
        if (cardNumber.length < 8) {
            _uiState.value = BankCardUiState.Error("Card number is too short")
            return
        }

        viewModelScope.launch {
            _uiState.value = BankCardUiState.Loading

            try {
                val response = repository.getCardInfo(cardNumber)
                _uiState.value = BankCardUiState.Success(response)
                saveToHistory(response)
            } catch (e: Exception) {
                _uiState.value = BankCardUiState.Error(
                    e.message ?: "Failed to get card information"
                )
            }
        }
    }

    private suspend fun saveToHistory(info: Info) {
        val jsonResponse = Json.encodeToString(info)
        historyRepository.saveHistoryItem(cardNumber, jsonResponse)
    }
}