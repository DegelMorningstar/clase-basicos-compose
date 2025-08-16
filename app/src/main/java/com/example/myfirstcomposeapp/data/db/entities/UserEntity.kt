package com.example.myfirstcomposeapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity(
	@PrimaryKey val uid: Int,
	val gender: String,
	val name: String,
	val location: String,
	val email: String,
	val login: String,
	val dob: Int,
	val registered: Int,
	val phone: String,
	val cell: String,
	val id: String,
	val picture: String,
	val nat: String
)