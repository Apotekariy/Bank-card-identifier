package com.example.bank_card_identifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bank_card_identifier.navigation.NavHost
import com.example.bank_card_identifier.ui.theme.BankcardidentifierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankcardidentifierTheme {
                    NavHost()
            }
        }
    }
}

