package com.example.bank_card_identifier.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main_screen")
    data object History : Screen("history_screen")
}