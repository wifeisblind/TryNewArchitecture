package com.gene.trynewarchitecture.extensions

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


fun InputStream.convertToString(): String {
    val sb = StringBuilder()
    var line: String?
    try {
        val reader = BufferedReader(InputStreamReader(this))
        while (reader.readLine().also { line = it } != null) {
            sb.append(line)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            this.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return sb.toString()
}