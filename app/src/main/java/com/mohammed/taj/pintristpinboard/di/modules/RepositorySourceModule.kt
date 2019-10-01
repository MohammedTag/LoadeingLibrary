package com.mohammed.taj.pintristpinboard.di.modules

import com.mohammed.taj.pintristpinboard.app.PinterestConstants
import com.mohammed.taj.pintristpinboard.data_layer.FeedsDataSource
import com.mohammed.taj.pintristpinboard.data_layer.FeedsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */


@Module(includes = [RemoteDataSourceModule::class])
class RepositorySourceModule {

    @Named(PinterestConstants.DaggerNamedValues.LOCAL_DATA_SOURCE)
    @Provides
    @Singleton
    fun providesRemoteDataSource(
        @Named(value = PinterestConstants.DaggerNamedValues.REMOTE_DATA_SOURCE)
        remoteDataSourceModule: FeedsDataSource
    ): FeedsDataSource =
        FeedsRepository(remoteDataSourceModule)
}