package com.mohammed.taj.pintristpinboard.data_layer

import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */
interface FeedsDataSource {
    suspend fun getUserFeed(page: String): List<Feeds>?
}