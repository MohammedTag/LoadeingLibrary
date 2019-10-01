package com.mohammed.taj.pintristpinboard.data_layer

import com.mohammed.taj.loadinglib.library_components.parsing.BaseParser
import com.mohammed.taj.loadinglib.library_components.requests.Request
import com.mohammed.taj.loadinglib.library_components.requests.RequestCall
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds
import com.mohammed.taj.pintristpinboard.data_layer.models.UserBean
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */

class FeedsRemoteDataSource(var jsonArrayParser: BaseParser<JSONArray>) : FeedsDataSource {
    override suspend fun getUserFeed(url: String): List<Feeds>? {

        return withContext(IO) {
            RequestCall(
                Request(
                    method = Request.GET,
                    url = url,
                    parser = jsonArrayParser
                )
            ).getData()
                ?.let { parseJsonArray(it) }
        }


    }

    private suspend fun parseJsonArray(data: JSONArray): List<Feeds> {
        val feedModels = mutableListOf<Feeds>()

        withContext(IO) {
            var i = 0
            while (i < data.length()) {
                try {
                    val jsonObject = data.getJSONObject(i)
                    val userJsonObject = jsonObject.getJSONObject("user")
                    val userModel = UserBean(
                        id = userJsonObject.getString("id"),
                        userName = userJsonObject.getString("username"),
                        profileIamge = userJsonObject.getJSONObject("profile_image").getString("large")
                    )
                    val feedModel = Feeds(
                        id = jsonObject.getString("id"),
                        color = jsonObject.getString("color"),
                        likes = jsonObject.getInt("likes"),
                        width = jsonObject.getInt("width"),
                        height = jsonObject.getInt("height"),
                        liked_by_user = jsonObject.getBoolean("liked_by_user"),
                        userModel = userModel
                    )

                    feedModels.add(feedModel)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                i++
            }
        }

        return feedModels
    }

}