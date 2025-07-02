package com.example.bank_card_identifier.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank_card_identifier.domain.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankCardViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    // UI состояние
    private val _uiState = MutableStateFlow<BankCardUiState>(BankCardUiState.Initial)
    val uiState: StateFlow<BankCardUiState> = _uiState.asStateFlow()

    // Введенный номер карты
    var cardNumber = ""
        private set

    // Обновление введенного номера
    fun updateCardNumber(newNumber: String) {
        cardNumber = newNumber.filter { it.isDigit() } // Фильтруем только цифры
    }

    // Запрос информации по карте
    fun fetchCardInfo() {
        // Валидация номера карты
        if (cardNumber.length < 8) {
            _uiState.value = BankCardUiState.Error("Card number is too short")
            return
        }

        viewModelScope.launch {
            _uiState.value = BankCardUiState.Loading

            try {
                val response = repository.getCardInfo(cardNumber)
                _uiState.value = BankCardUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = BankCardUiState.Error(
                    e.message ?: "Failed to get card information"
                )
            }
        }
    }
}