package com.mohammed.taj.pintristpinboard.domain_layer.usecases

import com.mohammed.taj.pintristpinboard.app.PinterestConstants
import com.mohammed.taj.pintristpinboard.data_layer.FeedsDataSource
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

class FeedsUseCase @Inject constructor(private val feedsRepository: FeedsDataSource):FeedsDataSource{
    override suspend fun getUserFeed(url: String): List<Feeds>? {
       return feedsRepository.getUserFeed(url)
    }

}

@Module(includes =[RepositorySourceModule::class] )
class FeedsUseCaseDependenciesModule {

    @Provides
    @Singleton
    fun providesFeedsUseCase(
        @Named(value = PinterestConstants.DaggerNamedValues.LOCAL_DATA_SOURCE)
        feedsRepository: FeedsDataSource
    ): FeedsDataSource =
        FeedsUseCase(
            feedsRepository
        )
}