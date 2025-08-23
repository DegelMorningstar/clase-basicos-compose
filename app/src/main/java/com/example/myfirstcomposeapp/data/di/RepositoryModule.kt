package com.example.myfirstcomposeapp.data.di

import com.example.myfirstcomposeapp.data.repositories.LocalUserRepositoryImpl
import com.example.myfirstcomposeapp.data.repositories.PreferencesRepositoryImpl
import com.example.myfirstcomposeapp.data.repositories.UserRepositoryImpl
import com.example.myfirstcomposeapp.domain.repositories.LocalUserRepository
import com.example.myfirstcomposeapp.domain.repositories.PreferencesRepository
import com.example.myfirstcomposeapp.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun provideLocalUserRepository(
        localUserRepositoryImpl: LocalUserRepositoryImpl
    ): LocalUserRepository

    @Binds
    abstract fun providePreferencesRepository(
        preferencesRepositoryImpl: PreferencesRepositoryImpl
    ): PreferencesRepository

}