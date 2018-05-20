package com.jastzeonic.navigationandpagingdemo

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jastzeonic.navigationandpagingdemo.adapter.GameListNormalAdatper
import com.jastzeonic.navigationandpagingdemo.database.GameInfoRepository
import com.jastzeonic.navigationandpagingdemo.database.RepositoryProvider

class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d("#####", "onCreateView")

        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.gameList)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
        val repository = RepositoryProvider.getDatabaseRepository(GameInfoRepository::class.java)
        repository.getAll().observe(this, Observer {
            Log.d("#####", "it:" + it?.size)
            recyclerView.adapter = GameListNormalAdatper(it ?: emptyList())
            recyclerView.adapter.notifyDataSetChanged()
        })




        return view
    }


}
