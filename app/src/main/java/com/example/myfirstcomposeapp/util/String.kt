package com.example.myfirstcomposeapp.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun String.onlyLettersAndSpaces(): Boolean {
    val regex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*$")
    return this.matches(regex)
}

fun String.formatToSimpleDate(): String {
    return try {
        val inputFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val date = OffsetDateTime.parse(this, inputFormat)
        "${date.dayOfMonth}/${date.monthValue}/${date.year}"
    } catch (e: Exception) {
        this // fallback si el parse falla
    }
}