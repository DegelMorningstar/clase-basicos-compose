package com.example.myfirstcomposeapp.data.mapper

import com.example.myfirstcomposeapp.data.db.entities.UserEntity
import com.example.myfirstcomposeapp.data.dto.UserDTO
import com.example.myfirstcomposeapp.domain.models.UserModel

fun UserDTO.fromDTOtoEntity(): UserEntity {
    return UserEntity(
        gender = this.gender,
        name = this.name.first + " " + this.name.last,
        location = "${this.location.street.number} ${this.location.street.name}, ${this.location.city}, ${this.location.state}, ${this.location.country}, Postal Code: ${this.location.postcode}",
        email = this.email,
        login = this.login.username,
        birthday = this.dob.date,
        age = this.registered.age.toString(),
        phone = this.phone,
        cell = this.cell,
        picture = this.picture.large,
        nat = this.nat,
        latitude = this.location.coordinates.latitude,
        longitude = this.location.coordinates.longitude
    )
}

fun UserDTO.fromDTOtoDomain(): UserModel {
    return UserModel(
        username = this.login.username,
        fullName = "${this.name.first} ${this.name.last}",
        image = this.picture.large,
        email = this.email,
        phone = this.phone,
        cellPhone = this.cell,
        birthday = this.dob.date,
        age = this.dob.age.toString(),
        address = "${this.location.street.number} ${this.location.street.name}, ${this.location.city}, ${this.location.state}, ${this.location.country}, Postal Code: ${this.location.postcode}",
        latitude = this.location.coordinates.latitude,
        longitude = this.location.coordinates.longitude
    )
}

fun UserEntity.fromEntitytoDomain(): UserModel {
    return UserModel(
        username = this.login,
        fullName = this.name,
        image = this.picture,
        email = this.email,
        phone = this.phone,
        cellPhone = this.cell,
        birthday = this.birthday,
        age = this.age,
        address = this.location,
        latitude = this.latitude,
        longitude = this.longitude
    )
}