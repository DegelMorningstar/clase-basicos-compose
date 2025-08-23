package com.example.myfirstcomposeapp.util

import android.util.Base64
import android.util.Log
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class AES {

    private val _MODE_ENCRYPT = "AES/ECB/PKCS5Padding"

    fun encrypt(input: String, key: String): String {
        var crypted: ByteArray? = null
        try {
            val skey = SecretKeySpec(key.toByteArray(), "AES")
            val cipher = Cipher.getInstance(_MODE_ENCRYPT)
            cipher.init(Cipher.ENCRYPT_MODE, skey)
            crypted = cipher.doFinal(input.toByteArray())
        } catch (e: Exception) {
            Log.e("error", e.toString())
        }
        return Base64.encodeToString(crypted, Base64.DEFAULT)
    }

    fun decrypt(input: String, key: String?): String {
        var output: ByteArray? = null

        val skey = SecretKeySpec(key?.toByteArray(), "AES")
        val cipher = Cipher.getInstance(_MODE_ENCRYPT)
        cipher.init(Cipher.DECRYPT_MODE, skey)
        output = cipher.doFinal(Base64.decode(input, 0))
        return String(output!!)
    }

}