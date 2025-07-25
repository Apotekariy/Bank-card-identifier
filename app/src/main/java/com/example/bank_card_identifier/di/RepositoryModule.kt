package com.example.bank_card_identifier.di

import com.example.bank_card_identifier.data.local.HistoryDao
import com.example.bank_card_identifier.data.remote.ApiService
import com.example.bank_card_identifier.data.repository.DataRepositoryImpl
import com.example.bank_card_identifier.data.repository.HistoryRepositoryImpl
import com.example.bank_card_identifier.domain.repository.DataRepository
import com.example.bank_card_identifier.domain.repository.HistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataRepository(apiService: ApiService): DataRepository {
        return DataRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(historyDao: HistoryDao): HistoryRepository {
        return HistoryRepositoryImpl(historyDao)
    }
}