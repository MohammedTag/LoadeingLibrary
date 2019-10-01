package com.mohammed.taj.pintristpinboard.data_layer

import com.mohammed.taj.pintristpinboard.app.PinterestConstants
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds
import com.mohammed.taj.pintristpinboard.di.modules.RepositorySourceModule
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */

class FeedsUseCase @Inject constructor(private val feedsRepository: FeedsDataSource,private val requestUrl: String){

    suspend fun run(page:String){
        feedsRepository.getUserFeed("$requestUrl/$page")
    }
}

@Module(includes =[RepositorySourceModule::class] )
class FeedsUseCaseDependenciesModule {

    @Provides
    @Singleton
    fun providesFeedsUseCase(
        @Named(value = PinterestConstants.DaggerNamedValues.LOCAL_DATA_SOURCE)
        feedsRepository:FeedsDataSource ,requestUrl: String
    ): FeedsUseCase = FeedsUseCase(feedsRepository,requestUrl)
}