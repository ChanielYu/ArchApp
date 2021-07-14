package com.auxy.archapp

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object AndroidTestUtils {
    fun readJsonFile(context: Context, filename: String): String? {
        var bufferedReader: BufferedReader? = null
        return try {
            bufferedReader = InputStreamReader(context.assets.open(filename)).buffered()
            val strBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                strBuilder.append(line)
                line = bufferedReader.readLine()
            }
            strBuilder.toString()
        } catch (ex: Exception) {
            null
        } finally {
            bufferedReader?.close()
        }
    }
}