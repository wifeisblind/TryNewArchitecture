package com.gene.trynewarchitecture.room.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.orhanobut.logger.Logger

object ExceptionListTypeConverter {

    val gson: Gson = Gson()

    @TypeConverter
    @JvmStatic
    fun exceptionListToString(list: List<Exception>?): String? {
        return list?.joinToString(",") { gson.toJson(it) }
    }

    @TypeConverter
    @JvmStatic
    fun stringToExceptionList(data: String?): List<Exception>? {
        return data?.let {
            it.split(",").map { str ->
                try {
                    gson.fromJson(data, Exception::class.java)
                } catch (ex: JsonSyntaxException) {
                    Logger.e(ex, "Cannot convert $str to number")
                    null
                }
            }
        }?.filterNotNull()
    }
}