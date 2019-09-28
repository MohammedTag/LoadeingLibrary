package com.mohammed.taj.loadinglib.library_components.parsing

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */
class JsonObjectParser :  BaseParser<JSONObject> {
    override fun parse(inputStream: InputStream): JSONObject {
        val responseStrBuilder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        var inputStr: String
        try {
            inputStr = bufferedReader.readLine()
            while (inputStr != null) {
                responseStrBuilder.append(inputStr)
            }
            inputStream.close()
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return JSONObject(responseStrBuilder.toString())
    }
}