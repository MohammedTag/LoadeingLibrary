package com.mohammed.taj.pintristpinboard.data_layer

import androidx.lifecycle.LiveData
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */
interface FeedsDataSource {
    fun getUserFeed(url: String): LiveData<List<Feeds>>
}