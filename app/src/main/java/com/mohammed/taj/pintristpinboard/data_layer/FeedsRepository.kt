package com.mohammed.taj.pintristpinboard.data_layer

import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */

class FeedsRepository (private val remoteHomeFeedDataSource: FeedsDataSource): FeedsDataSource{
    override suspend fun getUserFeed(url: String): List<Feeds>? {
        return remoteHomeFeedDataSource.getUserFeed(url)
    }

}