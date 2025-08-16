package com.example.myfirstcomposeapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstcomposeapp.data.db.dao.UserDao
import com.example.myfirstcomposeapp.data.db.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
	abstract fun userDao(): UserDao

	companion object {
		private var INSTANCE: UserDatabase? = null

		fun getInstance(context: Context): UserDatabase {
			return INSTANCE ?: synchronized(this){
				val instance = Room.databaseBuilder(
					context.applicationContext,
					UserDatabase::class.java,
					"user-db"
				).build()
				INSTANCE = instance
				instance
			}
		}
	}

}
