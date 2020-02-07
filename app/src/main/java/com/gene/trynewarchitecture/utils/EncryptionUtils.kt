package com.gene.trynewarchitecture.utils

import android.util.Base64


/**
 * Created by arvin on 2018/2/13.
 */

object EncryptionUtils {

    @JvmStatic
    fun base64Encryption(s: String?): String {
        return if (s.isNullOrEmpty()) "" else Base64.encodeToString(s.toByteArray(), Base64.NO_WRAP)
    }

    @JvmStatic
    fun getEncryptionBySecretKey(json: String?): String {
        TODO()
    }

    @JvmStatic
    fun getDescriptionBySecretKey(encryptionData: String?): String {
        TODO()
    }

    fun encryptionByTime(json: String): String {
        TODO()
    }

    fun descriptionByTime(encryptionData: String): String {
        TODO()
    }
}
