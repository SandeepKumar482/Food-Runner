package com.sandeep.foodrunner.adapter

import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.sandeep.foodrunner.R
import com.sandeep.foodrunner.activity.RestaurantMenuActivity
import com.sandeep.foodrunner.database.OrderEntity
import com.sandeep.foodrunner.database.RestaurantDatabase
import com.sandeep.foodrunner.model.RestaurantMenu

class RestaurantMenuAdapter(val context :Context,val itemList :ArrayList<RestaurantMenu>):RecyclerView.Adapter<RestaurantMenuAdapter.RestaurantMenuViewHolder>() {
    class RestaurantMenuViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val txtDishName:TextView=view.findViewById(R.id.txtDishName)
        val txtCount:TextView=view.findViewById(R.id.txtCount)
        val txtCost:TextView=view.findViewById(R.id.txtPrice)
        val btnAdd:Button=view.findViewById(R.id.btnAdd)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantMenuViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.recycler_restaurant_menu_single_row,parent,false)
        return RestaurantMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantMenuViewHolder, position: Int) {
        val listItem=itemList[position]
        holder.txtDishName.text=listItem.dish_name
        holder.txtCost.text=listItem.cost
        holder.txtCount.text=((position)+1).toString()
        val foodList=GetFavAsyncTask(context).execute().get()
        if(foodList.isNotEmpty()&&foodList.contains(listItem.id)){
            holder.btnAdd.setBackgroundColor(ContextCompat.getColor(context,R.color.Yellow))
            holder.btnAdd.text="Remove"
            holder.btnAdd.setOnClickListener {
                RestaurantMenuActivity.CartItems(context,listItem.restaurant_id,listItem.id,2)
                holder.btnAdd.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary))
                holder.btnAdd.text="Add"
                Toast.makeText(context,"Removed From Cart",Toast.LENGTH_SHORT)
            }
        }
        else{
            holder.btnAdd.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary))
            holder.btnAdd.text="Add"
            holder.btnAdd.setOnClickListener {
                RestaurantMenuActivity.CartItems(context,listItem.restaurant_id,listItem.id,1)
                holder.btnAdd.setBackgroundColor(ContextCompat.getColor(context,R.color.Yellow))
                holder.btnAdd.text="Remove"
                Toast.makeText(context,"Added to Cart",Toast.LENGTH_SHORT)

            }
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class GetFavAsyncTask(context: Context) : AsyncTask<Void, Void, List<String>>() {
        val db = Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurants-db")
            .build()

        override fun doInBackground(vararg params: Void?): List<String> {
            val list = db.orderDao().getAllOrders()
            val listOfIds = arrayListOf<String>()
            for (i in list) {
                listOfIds.add(i.foodId.toString())
            }
            return listOfIds
        }
    }
}