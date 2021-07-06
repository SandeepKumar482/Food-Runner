package com.sandeep.foodrunner.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.foodrunner.R
import com.sandeep.foodrunner.model.Restaurant
import com.squareup.picasso.Picasso


class HomeRecyclerAdapter(val context:Context, private val itemList:ArrayList<Restaurant>): RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
      val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_home_single_row,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val restaurant=itemList[position]
        holder.txtRestaurant.text=restaurant.RestaurantName
        holder.txtPrice.text= restaurant.FoodPrice
        holder.txtRestaurantRatings.text=restaurant.RestaurantRatting
        Picasso.get().load(restaurant.RestaurantImage).error(R.drawable.app_logo).into(holder.imgRestaurant)

    }

    override fun getItemCount(): Int {
      return itemList.size
    }
    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtRestaurant:TextView= view.findViewById(R.id.txtRestaurantName)
        val txtPrice:TextView=view.findViewById(R.id.txtPrice)
        val imgRestaurant:ImageView=view.findViewById(R.id.imgRestaurant)
        val txtRestaurantRatings:TextView=view.findViewById(R.id.txtRestaurantRating)

    }


}