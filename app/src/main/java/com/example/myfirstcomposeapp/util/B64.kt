package com.example.myfirstcomposeapp.util

import android.os.Build
import android.util.Base64

class B64 {
    fun decode(value: String?): String? {
        val result: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val decoder = java.util.Base64.getDecoder()
            val decodedByteArray = decoder.decode(value)
            String(decodedByteArray)
        } else {
            val decodedByteArrayApi7 = Base64.decode(value, 0)
            String(decodedByteArrayApi7)
        }
        return result
    }
}