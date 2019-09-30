package com.mohammed.taj.loadinglib.library_components.requests

import android.util.Log
import io.reactivex.Single
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */

class RequestCall<T>(private val request: Request<T>) {
    fun getData(): T? {
        var urlConnection: HttpURLConnection? = null
        Log.d("Url", request.url)

        var data: T? = null
        try {

            val uri = URL(request.url)
            urlConnection = uri.openConnection() as HttpURLConnection
            urlConnection.readTimeout = 15000
            urlConnection.connectTimeout = 15000
            urlConnection.requestMethod = request.method


            var inputStream = urlConnection.inputStream


            inputStream?.let {

                data = request.parser.parse(it)

            }
        } catch (e: Exception) {
            e.printStackTrace()
            urlConnection?.disconnect()
        } finally {
            urlConnection?.disconnect()
        }

            return data
    }
}