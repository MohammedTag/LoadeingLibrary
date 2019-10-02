package com.mohammed.taj.pintristpinboard.presentaion_layer.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds
import com.mohammed.taj.pintristpinboard.domain_layer.usecases.FeedsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */

class FeedsViewModel (private val feedsUseCase: FeedsUseCase):ViewModel(){

    var page = 0
    val homeFeedItemList= mutableListOf<Feeds>()

    val homeFeedItemObservable = MutableLiveData<List<Feeds>>()
    val loadMoreHomeFeedItemObservable = MutableLiveData<Boolean>()

     suspend fun getFeed(){
        page+=1
        feedsUseCase.run(page.toString())?.let { homeFeedItemList.addAll(it) }
        if (page==1){
            homeFeedItemObservable.postValue(homeFeedItemList)}else{
            loadMoreHomeFeedItemObservable.postValue(true)
        }
    }
}

class FeedsViewModelFactory(
    private val feedsUseCase: FeedsUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(FeedsViewModel::class.java))
            return FeedsViewModel(feedsUseCase) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Module
class FeedsViewModelFactoryModule {
    @Provides
    @Singleton

    fun providesFeedsViewModelFactoryFactory(
        feedsUseCase: FeedsUseCase
    ): FeedsViewModelFactory =
        FeedsViewModelFactory(feedsUseCase)
}