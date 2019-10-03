package com.mohammed.taj.pintristpinboard.data_layer

import com.mohammed.taj.loadinglib.library_components.parsing.JsonArrayParser
import com.mohammed.taj.loadinglib.library_components.requests.Request
import com.mohammed.taj.loadinglib.library_components.requests.RequestCall
import com.mohammed.taj.pintristpinboard.app.PinterestConstants
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds
import com.mohammed.taj.pintristpinboard.data_layer.models.UserBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */

class FeedsRemoteDataSource constructor(var jsonArrayParser: JsonArrayParser) : FeedsDataSource {
    override  suspend fun getUserFeed(page: String): List<Feeds>? {
       return networkCallHelper(page)
    }

    private suspend fun networkCallHelper(page: String): List<Feeds>? {
        return withContext(Dispatchers.Default) {
            val data =  async {
                RequestCall(
                    Request(
                        method = Request.GET,
                        url = PinterestConstants.BASE_URL+"/"+page,
                        parser = jsonArrayParser
                    )
                ).getData()

            }



            return@withContext data.await()?.let { parseJsonArray(it) }
        }
    }

    private fun parseJsonArray(data: JSONArray): List<Feeds> {
        val feedModels = mutableListOf<Feeds>()


            var i = 0
            while (i < data.length()) {
                try {
                    val jsonObject = data.getJSONObject(i)
                    val userJsonObject = jsonObject.getJSONObject("user")
                    val userModel = UserBean(
                        id = userJsonObject.getString("id"),
                        userName = userJsonObject.getString("username"),
                        profileImage = userJsonObject.getJSONObject("profile_image").getString("large")
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


        return feedModels
    }

}