package com.sandeep.foodrunner.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.foodrunner.Adapter.HomeRecyclerAdapter
import com.sandeep.foodrunner.R
import com.sandeep.foodrunner.model.Restaurant

class HomeFragment : Fragment() {
    private lateinit var layoutManager :RecyclerView.LayoutManager
    private lateinit var recyclerHome:RecyclerView
    private lateinit var recyclerAdapter: HomeRecyclerAdapter
     val restaurantList= arrayListOf<Restaurant>(Restaurant("Ramesh Ice Cream Parlour","250","4.4","R.id.Logo")
     ,Restaurant("Ramesh Ice Cream Parlour","250","4.4","R.id.appLogo"),
         Restaurant("Ramesh Ice Cream Parlour","250","4.4","R.id.appLogo"))


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_home, container, false)
        recyclerHome=view.findViewById(R.id.homeRecyclerView)
        layoutManager=LinearLayoutManager(activity)
        recyclerAdapter= HomeRecyclerAdapter(activity as Context,restaurantList)
        recyclerHome.adapter=recyclerAdapter
        recyclerHome.layoutManager=layoutManager

        return view

    }


}