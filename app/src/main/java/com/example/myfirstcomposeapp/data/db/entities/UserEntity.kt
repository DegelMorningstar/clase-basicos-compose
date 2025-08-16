package com.example.myfirstcomposeapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity(
	@PrimaryKey(autoGenerate = true) val uid: Int = 0,
	val gender: String,
	val name: String,
	val location: String,
	val email: String,
	val login: String,
	val birthday: String,
	val age: String,
	val phone: String,
	val cell: String,
	val picture: String,
	val nat: String,
	val latitude: String,
	val longitude: String,
)