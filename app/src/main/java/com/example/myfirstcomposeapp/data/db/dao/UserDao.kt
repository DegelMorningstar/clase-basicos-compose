package com.example.myfirstcomposeapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstcomposeapp.data.db.entities.UserEntity

@Dao
interface UserDao {
	@Query("SELECT * FROM UserEntity")
	fun getAll(): List<UserEntity>

	@Query("SELECT * FROM UserEntity WHERE uid IN (:userIds)")
	fun loadAllByIds(userIds: Int): List<UserEntity>

	@Insert
	fun insertAll(users: List<UserEntity>)

	@Delete
	fun delete(user: UserEntity)
}