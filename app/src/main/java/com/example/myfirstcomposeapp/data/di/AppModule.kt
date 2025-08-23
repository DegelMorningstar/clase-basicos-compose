package com.example.myfirstcomposeapp.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.myfirstcomposeapp.data.repositories.PreferencesRepositoryImpl.Companion.PREF_INSTANCE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(PREF_INSTANCE, Context.MODE_PRIVATE)
    }

}