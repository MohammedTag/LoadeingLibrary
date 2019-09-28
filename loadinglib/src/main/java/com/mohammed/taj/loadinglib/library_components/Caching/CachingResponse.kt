package com.mohammed.taj.loadinglib.library_components.Caching

import android.graphics.Bitmap
import androidx.collection.LruCache


/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */
class BitmapResponseCache {
    private var responseLruCache: LruCache<String, Bitmap>? = null

    init {
        responseMemoryCache()
    }

    private fun responseMemoryCache() {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        responseLruCache = LruCache(cacheSize)
    }

    fun addResponseToCache(key: String, response: Bitmap) {
        if (getResponseFromCache(key) == null) {
            responseLruCache!!.put(getCacheKey(key), response)
        }
    }

    fun getResponseFromCache(key: String): Bitmap? {
        return responseLruCache!!.get(getCacheKey(key))
    }

    fun clearResponseCache() {
        responseLruCache!!.evictAll()
    }

    fun removeResponse(cacheKey: String) {
        responseLruCache!!.remove(getCacheKey(cacheKey))
    }

    fun getCacheKey(url: String?): String {
        return url?.replace("[.:/,%?&=]".toRegex(), "+")?.replace("[+]+".toRegex(), "+")
            ?: throw RuntimeException("Null url passed in")
    }
    companion object {

        private var responseCache: BitmapResponseCache? = null

        val instance: BitmapResponseCache
            get() {
                if (responseCache == null)
                    responseCache = BitmapResponseCache()
                return responseCache!!
            }
    }
}