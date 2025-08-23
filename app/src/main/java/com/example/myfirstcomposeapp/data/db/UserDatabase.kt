package com.example.myfirstcomposeapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfirstcomposeapp.data.db.dao.UserDao
import com.example.myfirstcomposeapp.data.db.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
	abstract val userDao: UserDao
}
