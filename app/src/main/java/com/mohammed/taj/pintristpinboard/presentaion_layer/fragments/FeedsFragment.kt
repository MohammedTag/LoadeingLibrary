package com.mohammed.taj.pintristpinboard.presentaion_layer.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mohammed.taj.pintristpinboard.R
import com.mohammed.taj.pintristpinboard.app.App
import com.mohammed.taj.pintristpinboard.presentaion_layer.adapters.HomeAdapter
import kotlinx.android.synthetic.main.fragment_feeds.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FeedsFragment : Fragment(), CoroutineScope by MainScope() {

    @Inject
    lateinit var feedsViewModelFactory: FeedsViewModelFactory
    private lateinit var feedsViewModel: FeedsViewModel

    private var homeFeedAdapter = HomeAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_feeds, container, false)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).componant.inject(this)

        feedsViewModel =
            ViewModelProviders.of(this, feedsViewModelFactory).get(FeedsViewModel::class.java)

        launch {
            feedsViewModel.getFeed()
        }

        observeHomeFeedData()
        subscribeLoadMoreConversationMessagesSuccess()

    }

    private fun observeHomeFeedData() {
        feedsViewModel.homeFeedItemObservable.observe(this, Observer {
            homeFeedAdapter.setNewData(it)
        })
    }

    private fun subscribeLoadMoreConversationMessagesSuccess() {
        feedsViewModel.loadMoreHomeFeedItemObservable.observe(this, Observer {
            it?.let {
                loadMoreConversationSuccess()
            }
        })
    }

    private fun loadMoreConversationSuccess() {
        homeFeedAdapter.notifyDataSetChanged()
        homeFeedAdapter.loadMoreComplete()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as App).componant.inject(this)

        feedsViewModel =
            ViewModelProviders.of(this, feedsViewModelFactory).get(FeedsViewModel::class.java)
        usersRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = homeFeedAdapter
            /*addItemDecoration(SpacesItemDecoration(16))*/
        }
        homeFeedAdapter.apply {
            setEnableLoadMore(true)
            setOnLoadMoreListener({
                loadFeeds()
            }, usersRecyclerView)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        cancel()
    }

    fun loadFeeds() {
        //show Loading

        launch {
            feedsViewModel.getFeed()
        }

        feedsViewModel.homeFeedItemObservable.observe(this@FeedsFragment, Observer {
            homeFeedAdapter.addData(it)
        })
        homeFeedAdapter.notifyDataSetChanged()
        homeFeedAdapter.loadMoreComplete()
    }


    companion object {
        fun newInstance() = FeedsFragment()
    }


}


