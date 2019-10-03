package com.mohammed.taj.pintristpinboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds
import com.mohammed.taj.pintristpinboard.domain_layer.usecases.FeedsUseCase
import com.mohammed.taj.pintristpinboard.presentaion_layer.fragments.FeedsViewModel
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing


/**
 * Created by Mohammed Sayed Taguldeen on 03,October,2019
 * Cairo, Egypt.
 */

class FeedViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    var homeFeedItems = Feeds.TestBuilder.buildList()
    var page = "1"

    fun <T> T.toDeferred() = GlobalScope.async { this@toDeferred }

    fun <T : Any, R> KStubbing<T>.onBlocking(m: suspend T.() -> R)
            : OngoingStubbing<R> {
        return runBlocking { Mockito.`when`(mock.m()) }
    }



    private val loadHomeFeed = mock<FeedsUseCase> {
        onBlocking { getUserFeed(page) } doReturn homeFeedItems
    }


    private lateinit var viewModel: FeedsViewModel

    @Before
    fun setUp() {
        clearInvocations(loadHomeFeed)

        viewModel = FeedsViewModel(loadHomeFeed)
    }

    @Test
    fun loadFeedModelFromUseCase() {

        val myScope = GlobalScope
        runBlocking {
            myScope.launch {
                loadHomeFeed.getUserFeed(page)
                verify(loadHomeFeed).getUserFeed(page)
            }
        }
    }

}
