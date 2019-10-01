package com.mohammed.taj.pintristpinboard.di.modules

import com.mohammed.taj.loadinglib.library_components.parsing.BaseParser
import com.mohammed.taj.pintristpinboard.data_layer.FeedsDataSource
import com.mohammed.taj.pintristpinboard.data_layer.FeedsRemoteDataSource
import dagger.Module
import dagger.Provides
import org.json.JSONArray
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */


@Module

class RemoteDataSourceModule {
    @Provides
    @Singleton
    fun providesRemoteDataSource( jsonArrayParser: BaseParser<JSONArray>): FeedsDataSource =
        FeedsRemoteDataSource(jsonArrayParser)
}