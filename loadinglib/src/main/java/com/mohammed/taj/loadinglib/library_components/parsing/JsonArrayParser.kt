package com.mohammed.taj.loadinglib.library_components.parsing

import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */
class JsonArrayParser() : BaseParser<JSONArray> {
    override fun parse(inputStream: InputStream): JSONArray {
        val responseStrBuilder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        try {
            bufferedReader.forEachLine {
                responseStrBuilder.append(it)

            }

            inputStream.close()
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return JSONArray(responseStrBuilder.toString())


    }
}