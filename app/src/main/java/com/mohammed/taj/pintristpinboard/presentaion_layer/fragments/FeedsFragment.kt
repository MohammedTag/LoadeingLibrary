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
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass.
 */
class FeedsFragment : Fragment(),CoroutineScope {

    @Inject
    lateinit var feedsViewModelFactory: FeedsViewModelFactory
    private lateinit var feedsViewModel: FeedsViewModel

    private var homeFeedAdapter = HomeAdapter(mutableListOf())
    lateinit var masterJob: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        masterJob = Job()
        return inflater.inflate(R.layout.fragment_feeds, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as App).componant.inject(this)

        feedsViewModel = ViewModelProviders.of(this, feedsViewModelFactory).get(FeedsViewModel::class.java)
        usersRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = homeFeedAdapter
            /*addItemDecoration(SpacesItemDecoration(16))*/
        }
        homeFeedAdapter.apply {
            setEnableLoadMore(true)
            setOnLoadMoreListener({
                CoroutineScope(IO).launch{
                    loadFeeds()
                }

            }, usersRecyclerView)
        }

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + masterJob

    fun CoroutineScope.loadFeeds(){
        //show Loading
        this.launch {
            val data = withContext(IO){
                feedsViewModel.getFeed()
            }
            feedsViewModel.homeFeedItemObservable.observe(this@FeedsFragment, Observer {
                homeFeedAdapter.addData(it)
            })
            homeFeedAdapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        masterJob = Job()
    }

    companion object {
        fun newInstance() = FeedsFragment()
    }


}


