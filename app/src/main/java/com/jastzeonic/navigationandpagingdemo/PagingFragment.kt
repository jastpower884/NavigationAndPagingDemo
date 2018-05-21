package com.jastzeonic.navigationandpagingdemo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jastzeonic.navigationandpagingdemo.adapter.GameListPagingAdapter
import com.jastzeonic.navigationandpagingdemo.database.GameInfoRepository
import com.jastzeonic.navigationandpagingdemo.database.RepositoryProvider
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel
import com.jastzeonic.navigationandpagingdemo.paging.DataSourceFactory

class PagingFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var pagedList: LiveData<PagedList<GameInfoModel>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val status = MutableLiveData<Int>()

        status.observe(this, Observer {
            (recyclerView.adapter as GameListPagingAdapter).setState(it ?: 1)

        })


        val repository = RepositoryProvider.getDatabaseRepository(GameInfoRepository::class.java)
        val pageSize = 10

        val sourceFactory = DataSourceFactory(this, repository, status)
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(pageSize * 2)
                .setPageSize(pageSize)
                .build()
        pagedList = LivePagedListBuilder(sourceFactory, 30)
                .build()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d("#####", "onCreateView")

        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.gameList)


        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = GameListPagingAdapter({ view, url ->


            val result = DetailFragmentArgs.Builder()
                    .setUrl(url)
                    .build().toBundle()



            Navigation.findNavController(view).navigate(R.id.action_pagingFragment_to_detailFragment, result)
        })




        pagedList.observe(this, Observer {

            (recyclerView.adapter as GameListPagingAdapter).submitList(it)


        })



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pagedList.removeObservers(this)
    }


}
