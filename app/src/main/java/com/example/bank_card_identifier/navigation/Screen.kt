package com.example.bank_card_identifier.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    data object Main : Screen("main_screen", Icons.Filled.Home, "Card")
    data object History : Screen("history_screen", Icons.Filled.Info, "History")
}
val topLevelRoutes = listOf(Screen.Main, Screen.History)