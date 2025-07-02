package com.example.bank_card_identifier.presentation

import com.example.bank_card_identifier.domain.model.Info

sealed class BankCardUiState {
    data object Initial : BankCardUiState()
    data object Loading : BankCardUiState()
    data class Success(val cardInfo: Info) : BankCardUiState()
    data class Error(val message: String) : BankCardUiState()
}