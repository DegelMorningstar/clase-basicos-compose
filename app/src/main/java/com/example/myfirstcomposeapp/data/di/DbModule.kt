package com.example.myfirstcomposeapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.myfirstcomposeapp.data.db.UserDatabase
import com.example.myfirstcomposeapp.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: UserDatabase): UserDao = db.userDao

}