package com.mohammed.taj.pintristpinboard.di.modules

import com.mohammed.taj.loadinglib.library_components.parsing.JsonArrayParser
import com.mohammed.taj.pintristpinboard.app.PinterestConstants
import com.mohammed.taj.pintristpinboard.data_layer.FeedsDataSource
import com.mohammed.taj.pintristpinboard.data_layer.FeedsRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */


@Module(includes = [BaseParserModule::class])
class RemoteDataSourceModule {

    @Named(PinterestConstants.DaggerNamedValues.REMOTE_DATA_SOURCE)
    @Provides
    @Singleton
    fun providesRemoteDataSource( jsonArrayParser: JsonArrayParser): FeedsDataSource =
        FeedsRemoteDataSource(jsonArrayParser)
}