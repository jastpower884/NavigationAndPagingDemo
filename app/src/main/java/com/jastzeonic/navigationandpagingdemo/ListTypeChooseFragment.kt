package com.jastzeonic.navigationandpagingdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

class ListTypeChooseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_type_choose, container, false)
        view.findViewById<View>(R.id.button1).setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_listTypeChooseFragment_to_listFragment)

        }
        view.findViewById<View>(R.id.button2).setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_listTypeChooseFragment_to_pagingFragment)

        }

        return view
    }
}
