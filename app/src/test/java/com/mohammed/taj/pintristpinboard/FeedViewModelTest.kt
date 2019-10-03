package com.mohammed.taj.pintristpinboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds
import com.mohammed.taj.pintristpinboard.domain_layer.usecases.FeedsUseCase
import com.mohammed.taj.pintristpinboard.presentaion_layer.fragments.FeedsViewModel
import com.nhaarman.mockito_kotlin.clearInvocations
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


/**
 * Created by Mohammed Sayed Taguldeen on 03,October,2019
 * Cairo, Egypt.
 */

class FeedViewModelTest {

    //First Try Testing
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    var homeFeedItems = Feeds.TestBuilder.buildList()
    var page = "1"


    private val loadHomeFeed = mock<FeedsUseCase>{
        on {
            runBlocking {
                getUserFeed(page)
            }


        } doReturn (homeFeedItems)
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
