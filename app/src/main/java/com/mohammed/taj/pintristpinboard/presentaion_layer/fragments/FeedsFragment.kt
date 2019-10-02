package com.mohammed.taj.pintristpinboard.presentaion_layer.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mohammed.taj.pintristpinboard.R
import com.mohammed.taj.pintristpinboard.app.App
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FeedsFragment : Fragment() {
    @Inject
    lateinit var feedsViewModelFactory: FeedsViewModelFactory
    private lateinit var feedsViewModel: FeedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as App).compnant.inject(this)
        feedsViewModel = ViewModelProviders.of(this, feedsViewModelFactory).get(FeedsViewModel::class.java)


    }
}
